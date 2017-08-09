
package com.clement.poll.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.poll.dto.Poll;

@RepositoryRestResource(collectionResourceRel = "poll", path = "poll")
public interface PollRepository extends MongoRepository<Poll, String> {

}
