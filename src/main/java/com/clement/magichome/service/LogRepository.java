
package com.clement.magichome.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.magichome.object.Channel;
import com.clement.magichome.object.LogEntry;

@RepositoryRestResource(collectionResourceRel = "log", path = "log")
public interface LogRepository extends MongoRepository<LogEntry, String> {

}
