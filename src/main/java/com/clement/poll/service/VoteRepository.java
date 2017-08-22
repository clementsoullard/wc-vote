
package com.clement.poll.service;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.poll.dto.Vote;

@RepositoryRestResource(collectionResourceRel = "vote", path = "vote")
public interface VoteRepository extends MongoRepository<Vote, String> {

	/**
	 * 
	 * @param pollId
	 * @param tracer
	 * @return
	 */
	List<Vote> findByPollIdAndTracer(String pollId, String tracer);

	int countByAnswerAndPollId(Boolean answer, String pollId);
}
