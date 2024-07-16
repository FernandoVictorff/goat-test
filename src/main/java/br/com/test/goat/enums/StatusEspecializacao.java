package br.com.test.goat.enums;

public enum StatusEspecializacao {
    AGUARDANDO_DEFERIMENTO(0),
    DEFERIDO(1),
    INDEFERIDO(2);

    private int code;

    StatusEspecializacao(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
