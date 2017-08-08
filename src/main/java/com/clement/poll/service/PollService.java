package com.clement.poll.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.io.IOException;
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

import com.clement.poll.dto.Poll;
import com.clement.poll.dto.Participation;
import com.clement.poll.service.storage.StorageService;

@Component
public class PollService {
	@Autowired
	EventRepository eventRepository;

	@Autowired
	StorageService storageService;

	static final Logger LOG = LoggerFactory.getLogger(PollService.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public List<Poll> getCurrentOpenEvent() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -2);
			List<Poll> events = mongoTemplate
					.find(new Query(where("active").is(true).and("dateMaxVote").gt(calendar.getTime())), Poll.class);
			LOG.debug("Requete effectue");
			return events;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}


	/**
	 * This return the vent in the database. Here there is an intermediate layer
	 * to infame the participants that come after the max number is reached.
	 */
	public Poll getEvent(String idPoll) {
		Poll poll = eventRepository.findOne(idPoll);
		return poll;
	}

	public Poll saveEvent(Poll poll) throws IOException {
		eventRepository.save(poll);
		return poll;
	}
}
