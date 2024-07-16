package br.com.test.goat.validator;

import br.com.test.goat.exceptions.InvalidRequestExcepiton;

import static br.com.test.goat.validator.ValidatorConstants.EXCEEDS_MAX_LENGTH;
import static br.com.test.goat.validator.ValidatorConstants.MISSING;
import static io.micrometer.common.util.StringUtils.isBlank;
import static java.util.Objects.isNull;

public final class ValidatorUtils {

    private ValidatorUtils() {}

    public static void throwOnError(ValidationErrors validationErrors) {
        if (validationErrors.hasErrors()) {
            throw new InvalidRequestExcepiton(validationErrors);
        }
    }

    public static boolean validateRequired(String field, String fieldName, ValidationErrors validationErrors) {
        if (isBlank(field)) {
            validationErrors.add(fieldName, fieldName + MISSING);
            return false;
        }
        return true;
    }

    public static boolean validateRequired(Object field, String fieldName, ValidationErrors validationErrors) {
        if (isNull(field)) {
            validationErrors.add(fieldName, fieldName + MISSING);
            return false;
        }
        return true;
    }

    public static boolean validateMaxLength(
        String field,
        String fieldName,
        int maxLength,
        ValidationErrors validationErrors
    ) {
        if (!isBlank(field) && field.trim().length() > maxLength) {
            validationErrors.add(fieldName, fieldName + EXCEEDS_MAX_LENGTH);
            return false;
        }
        return true;
    }
}