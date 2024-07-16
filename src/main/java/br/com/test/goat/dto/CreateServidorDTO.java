package br.com.test.goat.dto;

import java.time.LocalDate;

public record CreateServidorDTO(
    String cpf,
    String email,
    String nome,
    LocalDate dataNascimento,
    Character sexo,
    Integer tipoServidor
) {}
