
package com.infosys.eventtracker.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.infosys.eventtracker.object.LogEntry;

@RepositoryRestResource(collectionResourceRel = "log", path = "log")
public interface LogRepository extends MongoRepository<LogEntry, String> {

}
