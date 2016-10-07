
package com.clement.eventtracker.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.eventtracker.dto.Participation;

@RepositoryRestResource(collectionResourceRel = "participation", path = "ws-participation")
public interface ParticipationRepository extends MongoRepository<Participation, String> {

}
