package com.nttdatabootcamp.springwithmongodb.service.Impl;

import com.nttdatabootcamp.springwithmongodb.commons.GenericServiceImpl;
import com.nttdatabootcamp.springwithmongodb.entity.ProductBank;
import com.nttdatabootcamp.springwithmongodb.repository.ProductBankRepository;
import com.nttdatabootcamp.springwithmongodb.service.ProductBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductBankServiceImpl extends GenericServiceImpl<ProductBank, String> implements ProductBankService {

    @Autowired
    private ProductBankRepository productBankRepository;

    @Override
    public void update(String id, ProductBank productBank) {
        Optional<ProductBank> optionalProductBank = productBankRepository.findById(id);

        if(optionalProductBank.isPresent()){
            ProductBank productBankUpdate = optionalProductBank.get();

            productBankUpdate.setDescription(productBank.getDescription());

            productBankRepository.save(productBankUpdate);
        }
    }

    @Override
    public CrudRepository<ProductBank, String> getDao() {
        return productBankRepository;
    }
}
