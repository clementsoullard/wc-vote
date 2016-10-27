package com.clement.eventtracker.controller;

import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clement.eventtracker.dto.Event;
import com.clement.eventtracker.dto.Participation;
import com.clement.eventtracker.dto.ParticipationStats;
import com.clement.eventtracker.service.EventService;
import com.clement.eventtracker.service.ParticipationDaoImpl;
import com.clement.eventtracker.service.ParticipationRepository;

@RestController
public class EventTrackerController {

	@Resource
	private ParticipationRepository participationRepository;

	@Resource
	private ParticipationDaoImpl participationDaoImpl;
	@Autowired
	private EventService eventService;

	@RequestMapping("/ws-participation-stats")
	public ParticipationStats getParticipationStats() throws Exception {
		ParticipationStats participationStats = new ParticipationStats();
		participationStats.setNbChild(participationDaoImpl.getChildNb());
		participationStats.setNbVegetarian(participationDaoImpl.getVegetarianNb());
		participationStats.setNbWomen(participationDaoImpl.getFemaleNb());
		return participationStats;
	}

	@RequestMapping("/ws-active-events")
	public List<Event> getActiveEvents() throws Exception {
		return eventService.getCurrentOpenEvent();
	}

	@RequestMapping("/ws-register/{idEvent}")
	public void patricipateEvent(@PathVariable("idEvent") String idEvent, @RequestBody Participation participation)
			throws Exception {
		participation.setIdr(ObjectId.get().toString());
		eventService.registerEvent(idEvent, participation);
	}
	@RequestMapping(value="/ws-unregister/{idEvent}/{id}",method=RequestMethod.DELETE)
	public void unregister(@PathVariable("idEvent") String idEvent, @PathVariable("id") String idParticipation)
			throws Exception {
		eventService.unregisterEvent(idEvent, idParticipation);
	}

}