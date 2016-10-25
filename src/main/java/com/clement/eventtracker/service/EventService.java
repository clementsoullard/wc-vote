package com.clement.eventtracker.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.clement.eventtracker.dto.Event;

@Component
public class EventService {
	@Autowired
	EventRepository eventRepository;

	static final Logger LOG = LoggerFactory.getLogger(EventService.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public List<Event> getCurrentOpenEvent() {
		try {
			List<Event> events = mongoTemplate.find(new Query(where("active").is(true)), Event.class);
			LOG.debug("Requete effectue");
			return events;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
}
