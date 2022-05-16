package com.nttdatabootcamp.springwithmongodb.service.Impl;

import com.nttdatabootcamp.springwithmongodb.commons.GenericServiceImpl;
import com.nttdatabootcamp.springwithmongodb.entity.Credit;
import com.nttdatabootcamp.springwithmongodb.repository.CreditRepository;
import com.nttdatabootcamp.springwithmongodb.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl extends GenericServiceImpl<Credit, String> implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Override
    public void update(String s, Credit credit) {

    }

    @Override
    public CrudRepository<Credit, String> getDao() {
        return creditRepository;
    }

    @Override
    public Credit searchCreditByIdClient(String idClient) {
        return creditRepository.findByIdClient(idClient);
    }
}
