package com.nttdatabootcamp.springwithmongodb.service;

import com.nttdatabootcamp.springwithmongodb.commons.GenericService;
import com.nttdatabootcamp.springwithmongodb.entity.Credit;

public interface CreditService extends GenericService<Credit, String> {

    Credit searchCreditByIdClient(String idClient);
}
