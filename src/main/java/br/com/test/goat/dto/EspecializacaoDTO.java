package br.com.test.goat.dto;

public record EspecializacaoDTO(
    Integer id,
    Integer idServidor,
    String area,
    String tipo,
    Integer cargaHoraria,
    Double valorTotalDoCurso
) {}
