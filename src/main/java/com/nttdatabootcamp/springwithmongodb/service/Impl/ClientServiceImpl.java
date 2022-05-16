package com.nttdatabootcamp.springwithmongodb.service.Impl;

import com.nttdatabootcamp.springwithmongodb.commons.GenericServiceImpl;
import com.nttdatabootcamp.springwithmongodb.entity.Client;
import com.nttdatabootcamp.springwithmongodb.repository.ClientRepository;
import com.nttdatabootcamp.springwithmongodb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, String> implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Override
  public void update(String id, Client client) {
    Optional<Client> clientOptional = clientRepository.findById(id);

    if(clientOptional.isPresent()){
      Client clientUpdate = clientOptional.get();

      clientUpdate.setFirstName(client.getFirstName());
      clientUpdate.setLastName(client.getLastName());
      clientUpdate.setDocumentNumber(client.getDocumentNumber());
      clientUpdate.setAge(client.getAge());
      clientUpdate.setType(client.getType());

      clientRepository.save(clientUpdate);
    }
  }

  @Override
  public CrudRepository<Client, String> getDao() {
    return clientRepository;
  }
}
