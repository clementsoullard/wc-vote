
package com.clement.magichome.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.magichome.object.BonPoint;
import com.clement.magichome.object.LogEntry;

@RepositoryRestResource(collectionResourceRel = "bonPoint", path = "bon-point")
public interface BonPointRepository extends MongoRepository<BonPoint, String> {

}
