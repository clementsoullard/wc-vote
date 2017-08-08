
package com.clement.poll.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.poll.dto.LineCaisse;

@RepositoryRestResource(collectionResourceRel = "line", path = "line-caisse",itemResourceRel="lineCaisse2")
public interface LineCaisseRepository extends MongoRepository<LineCaisse, String> {

}
