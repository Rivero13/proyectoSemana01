package com.nttdatabootcamp.springwithmongodb.service.Impl;

import com.nttdatabootcamp.springwithmongodb.commons.GenericServiceImpl;
import com.nttdatabootcamp.springwithmongodb.entity.Movement;
import com.nttdatabootcamp.springwithmongodb.repository.MovementRepository;
import com.nttdatabootcamp.springwithmongodb.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovementServiceImpl extends GenericServiceImpl<Movement, String> implements MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Override
    public void update(String id, Movement movement) {
        Optional<Movement> movementOptional = movementRepository.findById(id);

        if(movementOptional.isPresent()){
            Movement movementUpdate = movementOptional.get();

            movementUpdate.setDescription(movement.getDescription());
            movementUpdate.setAmount(movement.getAmount());
            movementUpdate.setDate(movement.getDate());
            movementUpdate.setIdAccount(movement.getIdAccount());

            movementRepository.save(movementUpdate);
        }
    }

    @Override
    public CrudRepository<Movement, String> getDao() {
        return movementRepository;
    }
}
