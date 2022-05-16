package com.nttdatabootcamp.springwithmongodb.repository;

import com.nttdatabootcamp.springwithmongodb.entity.ProductBank;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface ProductBankRepository extends MongoRepository<ProductBank, String> {
}
