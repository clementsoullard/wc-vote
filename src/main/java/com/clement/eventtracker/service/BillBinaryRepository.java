
package com.clement.eventtracker.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.eventtracker.dto.BillBinary;
import com.clement.eventtracker.dto.Event;
import com.clement.eventtracker.dto.Forfeit;

@RepositoryRestResource(collectionResourceRel = "bill", path = "bill")
public interface BillBinaryRepository extends MongoRepository<BillBinary, String> {

}
