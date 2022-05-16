package com.nttdatabootcamp.springwithmongodb.service;

import com.nttdatabootcamp.springwithmongodb.commons.GenericService;
import com.nttdatabootcamp.springwithmongodb.entity.BankAccount;

import java.util.List;

public interface BankAccountService extends GenericService<BankAccount, String> {
    BankAccount searchBankAccountByIdClient(String idClient);
    BankAccount searchBankAccountByTypeAndIdClient(String type, String idClient);
}
