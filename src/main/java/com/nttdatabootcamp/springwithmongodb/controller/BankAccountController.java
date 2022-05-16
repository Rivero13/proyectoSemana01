package com.nttdatabootcamp.springwithmongodb.controller;

import com.nttdatabootcamp.springwithmongodb.entity.BankAccount;
import com.nttdatabootcamp.springwithmongodb.service.BankAccountService;
import com.nttdatabootcamp.springwithmongodb.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-account")
public class BankAccountController {
  @Autowired
  private BankAccountService bankAccountService;

  @Autowired
  private ClientService clientService;

  private Logger log = LoggerFactory.getLogger(BankAccountController.class);

  @GetMapping(value = "/all")
  private List<BankAccount> getAll() {
    return bankAccountService.getAll();
  }

  @GetMapping(value = "/find/{id}")
  private String findById(@PathVariable String id) {
    String message = "Su saldo actual es: " + bankAccountService.findById(id).getAmount();
    return message;
  }

  @GetMapping(value = "/create")
  private String create(@RequestBody BankAccount bankAccount) {
    BankAccount obj = null;

    String createBankAccountErrorMessage = "The application could NOT create the BankAccount";
    String createBankAccountSuccessMessage = "The application created the BankAccount";

    bankAccount.maintenanceFee(bankAccount.getType());

    String typeClient = clientService.findById(bankAccount.getIdClient()).getType();

    if(typeClient.equals("Personal")){
      if(bankAccount.getType().equals("Saving") || bankAccount.getType().equals("Current account")){
        obj = bankAccountService.searchBankAccountByTypeAndIdClient(bankAccount.getType(), bankAccount.getIdClient());
      }

      if(obj == null){
        return bankAccountService.create(bankAccount) == null ? createBankAccountErrorMessage : createBankAccountSuccessMessage;
      }else{
        return createBankAccountErrorMessage;
      }

    }else{
      if(bankAccount.getType().equals("Saving") || bankAccount.getType().equals("Fixed term")){
        return createBankAccountErrorMessage;
      }else{
        return bankAccountService.create(bankAccount) == null ? createBankAccountErrorMessage : createBankAccountSuccessMessage;
      }
    }
  }

  @PutMapping(value = "/deposit/{id}/{amount}")
  public String deposit(@PathVariable String id, @PathVariable double amount){
    BankAccount currentBankAccount = bankAccountService.findById(id);

    double currentAmount = currentBankAccount.getAmount();
    currentAmount += amount;

    currentBankAccount.setAmount(currentAmount);

    bankAccountService.update(id, currentBankAccount);

    String message = "El monto actual es " + currentAmount;

    return message;
  }

  @PutMapping(value = "/withdraw/{id}/{amount}")
  private String withdraw(@PathVariable String id, @PathVariable double amount){
    BankAccount currentBankAccount = bankAccountService.findById(id);

    double currentAmount = currentBankAccount.getAmount();
    currentAmount -= amount;

    String messageSuccess = "El monto actual es " + currentAmount;
    String messageError = "No tiene fondos suficientes";

    if(currentAmount > 0){
      currentBankAccount.setAmount(currentAmount);

      bankAccountService.update(id, currentBankAccount);

      return messageSuccess;
    }else{
      return messageError;
    }
  }
  @GetMapping(value = "/findBy/{idClient}")
  private BankAccount searchBankAccountByIdClient(@PathVariable String idClient){
    return bankAccountService.searchBankAccountByIdClient(idClient);
  }

  @PutMapping(value = "/update/{id}")
  private ResponseEntity<BankAccount> updateBankAccount(@PathVariable String id, @RequestBody BankAccount bankAccount){
    bankAccountService.update(id, bankAccount);

    return new ResponseEntity<BankAccount>(HttpStatus.OK);
  }

  @DeleteMapping(value = "/delete/{id}")
  private ResponseEntity<BankAccount> deleteBankAccount(@PathVariable String id) {
    BankAccount bankAccount = bankAccountService.findById(id);

    if(bankAccount != null){
      bankAccountService.delete(id);
    }else{
      return new ResponseEntity<BankAccount>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<BankAccount>(HttpStatus.OK);
  }
}
