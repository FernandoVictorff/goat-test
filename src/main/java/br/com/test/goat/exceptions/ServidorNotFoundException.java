package br.com.test.goat.exceptions;

public class ServidorNotFoundException extends RuntimeException {
    public ServidorNotFoundException(String message) {
        super(message);
    }

    public String getMessage() {
        return String.format("{\"message\": \"%s\"}", super.getMessage());
    }
}
