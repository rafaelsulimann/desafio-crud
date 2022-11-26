package com.sulimann.desafio_crud.dtos;

import java.time.LocalDate;

import com.sulimann.desafio_crud.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO {
    
    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    @NotBlank(message = "Campo obrigatório")
    private String cpf;

    @Positive
    @NotNull(message = "Campo obrigatório")
    private Double income;

    @NotNull(message = "Campo obrigatório")
    @PastOrPresent(message = "Data precisa ser menor que a atual")
    private LocalDate birthDate;

    @NotNull(message = "Campo obrigatório")
    private Integer children;

    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }
}
