package com.clement.poll.controller;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.clement.poll.dto.Poll;
import com.clement.poll.dto.LineCaisse;
import com.clement.poll.dto.Participation;
import com.clement.poll.dto.ParticipationStats;
import com.clement.poll.service.PollService;
import com.clement.poll.service.LineCaisseRepository;
import com.clement.poll.service.ParticipationDaoImpl;
import com.clement.poll.service.ParticipationRepository;
import com.clement.poll.service.storage.StorageService;

@RestController
public class PollController {

	@Resource
	private ParticipationRepository participationRepository;

	@Resource
	private ParticipationDaoImpl participationDaoImpl;

	@Autowired
	private PollService pollService;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/ws-participation-stats")
	public ParticipationStats getParticipationStats() throws Exception {
		ParticipationStats participationStats = new ParticipationStats();
		return participationStats;
	}

	@GetMapping("/ws-active-polls")
	public List<Poll> getActiveEvents() throws Exception {
		List<Poll> polls = pollService.getCurrentOpenEvent();
		return polls;
	}

	@PostMapping("/ws-poll")
	public Poll updateEvent(@RequestBody Poll event) throws Exception {
		return pollService.saveEvent(event);
	}

	@GetMapping("/ws-event/{idEvent}")
	public Poll getEvent(@PathVariable("idEvent") String idEvent) throws Exception {
		return pollService.getEvent(idEvent);
	}

	@RequestMapping("/ws-register/{idEvent}")
	public void participateEvent(@PathVariable("idEvent") String idEvent, @RequestBody Participation participation)
			throws Exception {
		participation.setIdr(ObjectId.get().toString());
	}

	/**
	 * Called when clicking on payment of event.
	 * 
	 * @param idEvent
	 * @param idParticipation
	 * @param pay
	 * @throws Exception
	 */
	@RequestMapping("/ws-pay/{idEvent}/{idParticipation}/{pay}")
	public void pay(@PathVariable("idEvent") String idEvent, @PathVariable("idParticipation") String idParticipation,
			@PathVariable("pay") Boolean pay) throws Exception {
		participationDaoImpl.payEvent(idEvent, idParticipation, pay);
	}

	@RequestMapping(value = "/ws-download/{idEvent}.csv", produces = "text/csv", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource downloadCsv(@PathVariable("idEvent") String idEvent) throws Exception {
		return new FileSystemResource(participationDaoImpl.exportCSVFileParticipation(idEvent));
	}

}