package br.com.test.goat.config;

import br.com.test.goat.dto.ResponseError;
import br.com.test.goat.exceptions.InvalidRequestExcepiton;
import br.com.test.goat.exceptions.ServidorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ServidorNotFoundException.class)
    public ResponseEntity handleNotFoundException(Exception exception) {
        return ResponseEntity.status(NOT_FOUND.value()).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidRequestExcepiton.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ResponseError> handleInvalidRequestException(InvalidRequestExcepiton excepiton) {
        return excepiton
            .getValidationErrors()
            .stream()
            .map(e -> new ResponseError(e.getField(), e.getErrorCode()))
            .collect(Collectors.toList());
    }
}