package com.nttdatabootcamp.springwithmongodb.repository;

import com.nttdatabootcamp.springwithmongodb.entity.Movement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovementRepository extends MongoRepository<Movement, String> {
}
