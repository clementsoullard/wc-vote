
package com.infosys.eventtracker.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.infosys.eventtracker.dto.Participation;
import com.infosys.eventtracker.dto.Song;

@RepositoryRestResource(collectionResourceRel = "song", path = "ws-song")
public interface SongRepository extends MongoRepository<Song, String> {

}
