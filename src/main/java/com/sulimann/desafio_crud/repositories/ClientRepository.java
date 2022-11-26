package com.sulimann.desafio_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sulimann.desafio_crud.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
