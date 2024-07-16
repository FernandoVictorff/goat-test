package br.com.test.goat.dto;

import java.time.LocalDate;

public record ServidorDTO(
    Long matricula,
    String cpf,
    String email,
    String nome,
    LocalDate dataNascimento,
    Character sexo,
    Integer tipoServidor
) {}
