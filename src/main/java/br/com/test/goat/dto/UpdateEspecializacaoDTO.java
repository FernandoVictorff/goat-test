package br.com.test.goat.dto;

public record UpdateEspecializacaoDTO(
    String area,
    String tipo,
    Integer cargaHoraria,
    Double valorTotalDoCurso
) {}
