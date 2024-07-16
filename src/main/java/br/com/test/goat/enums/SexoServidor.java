package br.com.test.goat.enums;

import java.util.Arrays;

public enum SexoServidor {
    MASCULINO('M'),
    FEMININO('F');

    private Character sexo;
    SexoServidor(Character sexo) {
        this.sexo = sexo;
    }

    public Character getSexo() {
        return sexo;
    }

    public static SexoServidor getSexoServidorByCharacter(Character sexo) {
        return Arrays.stream(values()).filter(s -> s.getSexo().equals(sexo)).findFirst().get();
    }
}