package br.com.test.goat.exceptions;

public class EspecializacaoNotFoundException extends RuntimeException {

    public EspecializacaoNotFoundException(String message) {
        super(message);
    }

    public String getMessage() {
        return String.format("{\"message\": \"%s\"}", super.getMessage());
    }
}