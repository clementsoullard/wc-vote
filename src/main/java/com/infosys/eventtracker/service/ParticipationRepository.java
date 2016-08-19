
package com.infosys.eventtracker.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.infosys.eventtracker.dto.Participation;

@RepositoryRestResource(collectionResourceRel = "participation", path = "ws-participation")
public interface ParticipationRepository extends MongoRepository<Participation, String> {

}
