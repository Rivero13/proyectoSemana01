package com.nttdatabootcamp.springwithmongodb.service.Impl;

import com.nttdatabootcamp.springwithmongodb.commons.GenericServiceImpl;
import com.nttdatabootcamp.springwithmongodb.entity.BankAccount;
import com.nttdatabootcamp.springwithmongodb.repository.BankAccountRepository;
import com.nttdatabootcamp.springwithmongodb.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl extends GenericServiceImpl<BankAccount, String> implements BankAccountService {
  @Autowired
  private BankAccountRepository bankAccountRepository;

  @Override
  public void update(String id, BankAccount bankAccount) {
    Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(id);

    if(bankAccountOptional.isPresent()){
      BankAccount bankAccountUpdate = bankAccountOptional.get();

      bankAccountUpdate.setType(bankAccount.getType());
      bankAccountUpdate.setMaintenanceFee(bankAccount.getMaintenanceFee());
      bankAccountUpdate.setMaxMovement(bankAccount.getMaxMovement());
      bankAccountUpdate.setDate(bankAccount.getDate());
      bankAccountUpdate.setAmount(bankAccount.getAmount());
      bankAccountUpdate.setIdProduct(bankAccount.getIdProduct());
      bankAccountUpdate.setIdClient(bankAccount.getIdClient());

      bankAccountRepository.save(bankAccountUpdate);
    }
  }

  @Override
  public CrudRepository<BankAccount, String> getDao() {
    return bankAccountRepository;
  }

  @Override
  public BankAccount searchBankAccountByIdClient(String idClient) {
    return bankAccountRepository.findByIdClient(idClient);
  }

  @Override
  public BankAccount searchBankAccountByTypeAndIdClient(String type, String idClient) {
    return bankAccountRepository.findByTypeAndIdClient(type, idClient);
  }
}
