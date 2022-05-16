package com.nttdatabootcamp.springwithmongodb.controller;

import com.nttdatabootcamp.springwithmongodb.entity.Client;
import com.nttdatabootcamp.springwithmongodb.entity.Credit;
import com.nttdatabootcamp.springwithmongodb.service.ClientService;
import com.nttdatabootcamp.springwithmongodb.service.CreditService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/all")
    private List<Credit> getAllCredits(){
        return creditService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    private String findCreditById(@PathVariable String id){
        String message = "Su crédito actual disponible es: " + creditService.findById(id).getLimitCredit();
        return message;
    }

    @GetMapping(value = "/create")
    private String createCredit(@RequestBody Credit credit){

        Credit obj = null;
        String createCreditErrorMessage = "The application could NOT create the Credit";
        String createCreditSuccessMessage = "The application created the Credit";

        String typeClient = clientService.findById(credit.getIdClient()).getType();
        credit.setAmountCredit(typeClient);

        if(typeClient.equals("Personal")){
            obj = creditService.searchCreditByIdClient(credit.getIdClient());

            if(obj == null){
                return creditService.create(credit) == null ? createCreditErrorMessage : createCreditSuccessMessage;
            }else{
                return createCreditErrorMessage;
            }
        }else{
            return creditService.create(credit) == null ? createCreditErrorMessage : createCreditSuccessMessage;
        }
    }

    @PutMapping(value = "/update/{id}")
    private ResponseEntity<Credit> updateCredit(@PathVariable String id, @RequestBody Credit credit){
        creditService.update(id, credit);

        return new ResponseEntity<Credit>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Credit> deleteCredit(@PathVariable String id){
        Credit credit = creditService.findById(id);

        if(credit != null){
            creditService.delete(id);
        }else{
            return new ResponseEntity<Credit>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Credit>(HttpStatus.OK);
    }

    @PutMapping(value = "/consumption/{id}/{amount}")
    private String consumption(@PathVariable String id, @PathVariable double amountCredit){
        Credit creditCurrent = creditService.findById(id);

        double currentCredit = creditCurrent.getLimitCredit();
        currentCredit -= amountCredit;

        String messageSuccess = "El credito actual es " + currentCredit;
        String messageError = "Ha llegado a su límite de crédito";

        if(currentCredit > 0){
            creditCurrent.setLimitCredit(currentCredit);

            creditService.update(id, creditCurrent);

            return messageSuccess;
        }else{
            return messageError;
        }
    }
}
