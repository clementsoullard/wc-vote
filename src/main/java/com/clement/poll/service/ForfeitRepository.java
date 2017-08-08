
package com.clement.poll.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.poll.dto.Forfeit;

@RepositoryRestResource(collectionResourceRel = "forfeit", path = "forfeit")
public interface ForfeitRepository extends MongoRepository<Forfeit, String> {

}
