
package com.clement.poll.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.poll.dto.Participation;

@RepositoryRestResource(collectionResourceRel = "participation", path = "participation")
public interface ParticipationRepository extends MongoRepository<Participation, String> {

}
