package com.nttdatabootcamp.springwithmongodb.controller;

import com.nttdatabootcamp.springwithmongodb.entity.ProductBank;
import com.nttdatabootcamp.springwithmongodb.service.ProductBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productBank")
public class ProductBankController {

    @Autowired
    private ProductBankService productBankService;

    @GetMapping(value = "/all")
    public List<ProductBank> getAll() {
        return productBankService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public ProductBank findById(@PathVariable String id) {
        return productBankService.findById(id);
    }

    @GetMapping(value = "/create")
    public String create(@RequestBody ProductBank productBank) {
        String createProductBankErrorMessage = "The application could NOT create the ProductBank";
        String createProductBankSuccessMessage = "The application created the ProductBank";

        return productBankService.create(productBank) == null ? createProductBankErrorMessage : createProductBankSuccessMessage;
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ProductBank> update(@PathVariable String id, @RequestBody ProductBank productBank) {
        productBankService.update(id, productBank);
        return new ResponseEntity<ProductBank>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ProductBank> delete(@PathVariable String id) {
        ProductBank productBank = productBankService.findById(id);

        if(productBank != null){
            productBankService.delete(id);
        }else{
            return new ResponseEntity<ProductBank>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ProductBank>(HttpStatus.OK);
    }

}
