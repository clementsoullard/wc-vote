
package com.infosys.eventtracker.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.infosys.eventtracker.dto.Participation;
import com.infosys.eventtracker.object.BonPoint;

@RepositoryRestResource(collectionResourceRel = "participation", path = "participation")
public interface ParticipationRepository extends MongoRepository<Participation, String> {

}
