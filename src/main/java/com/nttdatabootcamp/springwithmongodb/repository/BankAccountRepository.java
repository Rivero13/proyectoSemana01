package com.nttdatabootcamp.springwithmongodb.repository;

import com.nttdatabootcamp.springwithmongodb.entity.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BankAccountRepository extends MongoRepository<BankAccount, String> {

    BankAccount findByIdClient(String idClient);
    BankAccount findByTypeAndIdClient(String type, String idClient);
}
