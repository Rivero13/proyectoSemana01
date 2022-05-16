package com.nttdatabootcamp.springwithmongodb.controller;

import com.nttdatabootcamp.springwithmongodb.entity.Client;
import com.nttdatabootcamp.springwithmongodb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/all")
    private List<Client> getAllClients(){
        return clientService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    private Client findClientById(@PathVariable String id){
        return clientService.findById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Client> deleteClient(@PathVariable String id){
        Client client = clientService.findById(id);

        if(client != null){
            clientService.delete(id);
        }else{
            return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Client>(HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    private ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody Client client){
        clientService.update(id, client);
        return new ResponseEntity<Client>(HttpStatus.OK);
    }

    @GetMapping(value = "/create")
    private String Client(@RequestBody Client client){
        String createClientErrorMessage = "The application could NOT create the client";
        String createClientSuccessMessage = "The application created the client";

        return clientService.create(client) == null ? createClientErrorMessage : createClientSuccessMessage;
    }
}
