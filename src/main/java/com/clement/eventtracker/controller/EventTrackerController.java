package com.clement.eventtracker.controller;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.clement.eventtracker.dto.Event;
import com.clement.eventtracker.dto.LineCaisse;
import com.clement.eventtracker.dto.Participation;
import com.clement.eventtracker.dto.ParticipationStats;
import com.clement.eventtracker.service.EventService;
import com.clement.eventtracker.service.LineCaisseRepository;
import com.clement.eventtracker.service.ParticipationDaoImpl;
import com.clement.eventtracker.service.ParticipationRepository;
import com.clement.eventtracker.service.storage.StorageService;

@RestController
public class EventTrackerController {

	@Resource
	private ParticipationRepository participationRepository;

	@Resource
	private ParticipationDaoImpl participationDaoImpl;

	@Autowired
	private EventService eventService;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
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

//	@RequestMapping("/ws-add-question/{idEvent}")
//	public List<Event> addQuestion(@PathVariable("idEvent") String idEvent) throws Exception {
//		return eventService.getCurrentOpenEvent();
//	}

	@RequestMapping("/ws-event/{idEvent}")
	public Event getEvent(@PathVariable("idEvent") String idEvent) throws Exception {
		return eventService.getEvent(idEvent);
	}

	@RequestMapping("/ws-register/{idEvent}")
	public void participateEvent(@PathVariable("idEvent") String idEvent, @RequestBody Participation participation)
			throws Exception {
		participation.setIdr(ObjectId.get().toString());
		eventService.registerEvent(idEvent, participation);
	}

	@RequestMapping("/ws-pay/{idEvent}/{idParticipation}/{pay}")
	public void pay(@PathVariable("idEvent") String idEvent, @PathVariable("idParticipation") String idParticipation,
			@PathVariable("pay") Boolean pay) throws Exception {
		participationDaoImpl.payEvent(idEvent, idParticipation, pay);
	}

	@RequestMapping(value = "/ws-unregister/{idEvent}/{id}", method = RequestMethod.DELETE)
	public void unregister(@PathVariable("idEvent") String idEvent, @PathVariable("id") String idParticipation)
			throws Exception {
		eventService.unregisterEvent(idEvent, idParticipation);
	}

	@RequestMapping(value = "/ws-download/{idEvent}.csv", produces = "text/csv", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource downloadCsv(@PathVariable("idEvent") String idEvent) throws Exception {
		return new FileSystemResource(participationDaoImpl.exportCSVFileParticipation(idEvent));
	}

}