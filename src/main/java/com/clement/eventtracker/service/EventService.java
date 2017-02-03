package com.clement.eventtracker.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.clement.eventtracker.dto.Event;
import com.clement.eventtracker.dto.Participation;

@Component
public class EventService {
	@Autowired
	EventRepository eventRepository;

	static final Logger LOG = LoggerFactory.getLogger(EventService.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public List<Event> getCurrentOpenEvent() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -2);
			List<Event> events = mongoTemplate.find(new Query(where("active").is(true).and("date").gt(new Date())),
					Event.class);
			LOG.debug("Requete effectue");
			return events;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Register a user to the event.
	 * 
	 * @param idEvent
	 * @param participation
	 */
	public void registerEvent(String idEvent, Participation participation) {
		Event event = eventRepository.findOne(idEvent);
		Date maxRegistrationDate = event.getDateMaxRegistration();
		Date registrationDate = new Date();
		participation.setRegistrationDate(registrationDate);

		if (maxRegistrationDate != null && registrationDate.after(maxRegistrationDate)) {
			participation.setInfamed(true);
		}
		event.addParticipation(participation);
		eventRepository.save(event);
	}

	public void unregisterEvent(String idEvent, String idParticipation) {
		mongoTemplate.updateMulti(new Query(),
				new Update().pull("participations", new Query(Criteria.where("id").is(idParticipation))), Event.class);
	}

	/**
	 * This return the vent in the database. Here there is an intermediate layer
	 * to infame the participants that come after the max number is reached.
	 */
	public Event getEvent(String idEvent) {
		Event event = eventRepository.findOne(idEvent);
		Integer maxParticipants = event.getMaxParticipant();
		List<Participation> participations = event.getParticipations();
		/**
		 * There is a sophisticated processing here, the reason what we count in
		 * reverse order is that the result should appear the last registered
		 * first.
		 */
		Collections.sort(participations, new Comparator<Participation>() {
			@Override
			public int compare(Participation o1, Participation o2) {
				if (o1.getRegistrationDate() == null) {
					return 1;
				} else if (o2.getRegistrationDate() == null) {
					return -1;
				} else {
					return o1.getRegistrationDate().after(o2.getRegistrationDate()) ? -1 : 1;
				}
			}
		});
		/**
		 * If the user register after the maximum particpant is reached, then it
		 * is marked as infamed.
		 * 
		 */
		if (maxParticipants != null) {
			for (int i = participations.size() - 1; i > -1; i--) {
				Participation participation = participations.get(i);
				if (participations.size() - 1 - i >= maxParticipants) {
					participation.setInfamed(true);
				}
			}
		}
		return event;
	}
}
