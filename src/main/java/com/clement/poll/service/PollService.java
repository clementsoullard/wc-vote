package com.clement.poll.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.clement.poll.dto.ChartData;
import com.clement.poll.dto.Poll;
import com.clement.poll.dto.Slice;
import com.clement.poll.service.storage.StorageService;

@Component
public class PollService {

	@Autowired
	VoteRepository voteRepository;

	@Autowired
	PollRepository pollRepository;

	@Autowired
	StorageService storageService;

	static final Logger LOG = LoggerFactory.getLogger(PollService.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public List<Poll> getCurrentOpenPoll() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -2);
			List<Poll> polls = mongoTemplate.find(new Query(where("dateMaxVote").gt(calendar.getTime())), Poll.class);
			LOG.debug("Requete effectue");
			/**
			 * If a poll is after the date max of vote, then it is closed.
			 */
			Date date = new Date();
			for (Poll poll : polls) {
				if (poll.getDateMaxVote().before(date)) {
					poll.setActive(false);
				}
				int voteFor = voteRepository.countByAnswerAndPollId(true, poll.getIdr());
				poll.setVoteFor(voteFor);
				int voteAgainst = voteRepository.countByAnswerAndPollId(false, poll.getIdr());
				poll.setVoteAgainst(voteAgainst);

			}

			return polls;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * This return the vent in the database. Here there is an intermediate layer
	 * to infame the participants that come after the max number is reached.
	 */
	public Poll getPoll(String idPoll) {
		Poll poll = pollRepository.findOne(idPoll);
		return poll;
	}

	public Poll savePoll(Poll poll) throws IOException {
		pollRepository.save(poll);
		return poll;
	}

	/**
	 * 
	 * @param idPoll
	 * @return
	 */
	public List<Slice> getPollResult(String idPoll) {
		List<Slice> slices = new ArrayList<Slice>();
		int voteFor = voteRepository.countByAnswerAndPollId(true, idPoll);
		int voteAgainst = voteRepository.countByAnswerAndPollId(false, idPoll);
		slices.add(new Slice("Yes", 0F + voteFor));
		slices.add(new Slice("No", 0F + voteAgainst));
		return slices;
	}
}
