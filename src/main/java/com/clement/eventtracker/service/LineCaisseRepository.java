
package com.clement.eventtracker.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clement.eventtracker.dto.Song;

@RepositoryRestResource(collectionResourceRel = "song", path = "ws-song")
public interface SongRepository extends MongoRepository<Song, String> {

}
