package com.sulimann.desafio_crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sulimann.desafio_crud.dtos.ClientDTO;
import com.sulimann.desafio_crud.entities.Client;
import com.sulimann.desafio_crud.repositories.ClientRepository;
import com.sulimann.desafio_crud.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = clientRepository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long clientId){
        Client entity = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Cliente inexistente. id: " + clientId));
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO){
        Client entity = new Client();
        convertDtoToEntity(entity, clientDTO);
        entity = clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long clientId, ClientDTO clientDTO){
        try {
            Client entity = clientRepository.getReferenceById(clientId);
            convertDtoToEntity(entity, clientDTO);
            entity = clientRepository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente inexistente. id: " + clientId);
        }
    }

    @Transactional
    public void delete(Long clientId){
        try {
            clientRepository.deleteById(clientId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Cliente inexistente. id: " + clientId);
        }
    }

    private void convertDtoToEntity(Client entity, ClientDTO clientDTO){
        entity.setName(clientDTO.getName());
        entity.setCpf(clientDTO.getCpf());
        entity.setIncome(clientDTO.getIncome());
        entity.setBirthDate(clientDTO.getBirthDate());
        entity.setChildren(clientDTO.getChildren());
    }

    
}
