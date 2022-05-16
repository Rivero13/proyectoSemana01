package com.nttdatabootcamp.springwithmongodb.repository;

import com.nttdatabootcamp.springwithmongodb.entity.Credit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditRepository extends MongoRepository<Credit, String> {

    Credit findByIdClient(String idClient);
}
