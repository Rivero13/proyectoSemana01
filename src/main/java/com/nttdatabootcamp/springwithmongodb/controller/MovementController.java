package com.nttdatabootcamp.springwithmongodb.controller;

import com.nttdatabootcamp.springwithmongodb.entity.Movement;
import com.nttdatabootcamp.springwithmongodb.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movements")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @GetMapping(value = "/all")
    private List<Movement> getAllMovements(){
        return movementService.getAll();
    }

    @GetMapping(value = "/create")
    private String createMovement(@RequestBody Movement movement){
        String createMovementErrorMessage = "The application could NOT create the Movement";
        String createMovementSuccessMessage = "The application created the Movement";

        return movementService.create(movement) == null ? createMovementErrorMessage : createMovementSuccessMessage;
    }
}
