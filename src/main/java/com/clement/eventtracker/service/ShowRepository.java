
package com.clement.eventtracker.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.eventtracker.dto.Show;

@RepositoryRestResource(collectionResourceRel = "show", path = "show")
public interface ShowRepository extends MongoRepository<Show, String> {

}
