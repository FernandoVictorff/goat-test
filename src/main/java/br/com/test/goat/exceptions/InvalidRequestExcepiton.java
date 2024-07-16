package br.com.test.goat.exceptions;

import br.com.test.goat.validator.ValidationError;
import br.com.test.goat.validator.ValidationErrors;

public class InvalidRequestExcepiton extends RuntimeException {
    private final ValidationErrors validationErrors;
    public InvalidRequestExcepiton(ValidationErrors validationErrors) {
        super(validationErrors.toString());
        this.validationErrors = validationErrors;
    }

    public InvalidRequestExcepiton(ValidationError validationError) {
        this(new ValidationErrors().add(validationError));
    }

    public ValidationErrors getValidationErrors() {
        return validationErrors;
    }
}