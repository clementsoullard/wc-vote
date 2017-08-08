
package com.clement.poll.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.poll.dto.Show;

@RepositoryRestResource(collectionResourceRel = "show", path = "show")
public interface ShowRepository extends MongoRepository<Show, String> {

}
