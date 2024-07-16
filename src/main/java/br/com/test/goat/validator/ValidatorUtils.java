package br.com.test.goat.validator;

import br.com.test.goat.exceptions.InvalidRequestExcepiton;

public final class ValidatorUtils {

    private ValidatorUtils() {}

    public static void throwOnError(ValidationErrors validationErrors) {
        if (validationErrors.hasErrors()) {
            throw new InvalidRequestExcepiton(validationErrors);
        }
    }
}