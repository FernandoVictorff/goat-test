package br.com.test.goat.enums;

import java.util.Arrays;

public enum TipoServidor {
    PROFESSOR(1),
    TECNICO(2);

    private Integer tipo;

    TipoServidor(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public static TipoServidor getTipoServidorByTipo(Integer tipo) {
        return Arrays.stream(values()).filter(t -> t.getTipo().equals(tipo)).findFirst().get();
    }
}
