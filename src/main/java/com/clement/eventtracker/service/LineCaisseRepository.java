
package com.clement.eventtracker.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.eventtracker.dto.LineCaisse;

@RepositoryRestResource(collectionResourceRel = "line", path = "line-caisse")
public interface LineCaisseRepository extends MongoRepository<LineCaisse, String> {

}
