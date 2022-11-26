package com.sulimann.desafio_crud.controllers.handlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sulimann.desafio_crud.dtos.CustomErrorDTO;
import com.sulimann.desafio_crud.dtos.FieldMessageDTO;
import com.sulimann.desafio_crud.dtos.ValidationErrorDTO;
import com.sulimann.desafio_crud.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO error = new CustomErrorDTO(LocalDateTime.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO error = new ValidationErrorDTO(LocalDateTime.now(), status.value(), "Dados inválidos", request.getRequestURI());
        for(FieldError f : e.getBindingResult().getFieldErrors()){
            error.addError(new FieldMessageDTO(f.getField(), f.getDefaultMessage()));
        }
        return ResponseEntity.status(status).body(error);
    }

    
}
