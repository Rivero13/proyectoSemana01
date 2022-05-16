package com.nttdatabootcamp.springwithmongodb.repository;

import com.nttdatabootcamp.springwithmongodb.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {

  List<Client> findAll();

  List<Client> searchById(@Param("id") String id);

}
