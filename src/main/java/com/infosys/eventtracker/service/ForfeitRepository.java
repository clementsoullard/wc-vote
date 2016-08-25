
package com.infosys.eventtracker.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.infosys.eventtracker.dto.Forfeit;

@RepositoryRestResource(collectionResourceRel = "forfeit", path = "ws-forfeit")
public interface ForfeitRepository extends MongoRepository<Forfeit, String> {

}
