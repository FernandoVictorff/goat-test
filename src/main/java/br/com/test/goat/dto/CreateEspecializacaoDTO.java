package br.com.test.goat.dto;

public record CreateEspecializacaoDTO(
    Integer idServidor,
    String area,
    String tipo,
    Integer cargaHoraria,
    Double valorTotalDoCurso
) {}
