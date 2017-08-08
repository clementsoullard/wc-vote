
package com.clement.poll.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.poll.dto.BillBinary;
import com.clement.poll.dto.Poll;
import com.clement.poll.dto.Forfeit;

@RepositoryRestResource(collectionResourceRel = "bill", path = "bill")
public interface BillBinaryRepository extends MongoRepository<BillBinary, String> {

}
