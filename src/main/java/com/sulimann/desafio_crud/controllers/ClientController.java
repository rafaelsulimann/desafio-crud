package com.sulimann.desafio_crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sulimann.desafio_crud.dtos.ClientDTO;
import com.sulimann.desafio_crud.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@RequestBody @Valid ClientDTO clientDTO){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.insert(clientDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.update(id, clientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
