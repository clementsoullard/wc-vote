package com.clement.poll.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.clement.poll.dto.Vote;
import com.clement.poll.dto.ParticipationStats;
import com.clement.poll.dto.Poll;
import com.clement.poll.dto.TransactionResult;
import com.clement.poll.service.VoteDaoImpl;
import com.clement.poll.service.VoteRepository;
import com.clement.poll.service.PollService;

@RestController
public class PollController {

	final static Logger LOGGER = LoggerFactory.getLogger(PollController.class);

	@Resource
	private VoteRepository voteRepository;

	@Resource
	private VoteDaoImpl participationDaoImpl;

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
		return pollService.savePoll(event);
	}

	@GetMapping("/ws-vote/{idVote}")
	public Poll getEvent(@PathVariable("idVote") String idEvent) throws Exception {
		return pollService.getEvent(idEvent);
	}

	/**
	 * 
	 * @param idEvent
	 * @param vote
	 * @throws Exception
	 */
	@RequestMapping("/ws-vote/{idPoll}")
	public ResponseEntity<TransactionResult> votePoll(@CookieValue("client-autogen") String identifierClient,
			@PathVariable("idPoll") String idPoll, @RequestBody Vote vote) throws Exception {
		ResponseEntity<TransactionResult> responseEntity;
		vote.setVoteDate(new Date());
		vote.setTracer(identifierClient);
		List<Vote> votes = voteRepository.findByPollIdAndTracer(idPoll, identifierClient);
		if (votes.size() == 0) {
			voteRepository.save(vote);
			responseEntity = new ResponseEntity<TransactionResult>(
					new TransactionResult("The vote has been successfully saved."), HttpStatus.OK);
		} else {

			responseEntity = new ResponseEntity<TransactionResult>(new TransactionResult("You cannot vote twice."),
					HttpStatus.CONFLICT);
			LOGGER.info("Deja voté pour ce sondage on ne prend pas en compte");
		}

		return responseEntity;
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