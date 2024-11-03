package com.example.mongodb.core.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        String errorMessage = messageSource.getMessage(
                ex.getMessage() + ".message", new Object[]{}, ex.getMessage(), Locale.getDefault()
        );

        String errorCodeStr = messageSource.getMessage(
                ex.getMessage() + ".code", new Object[]{}, "0", Locale.getDefault()
        );

        int errorCode;
        try {
            errorCode = Integer.parseInt(Objects.requireNonNull(errorCodeStr));
        } catch (NumberFormatException e) {
            errorCode = 0;
        }

        int httpStatusValue = ex.getHttpStatusCode() != null ? ex.getHttpStatusCode() : 400;

        RestError restError = new RestError(httpStatusValue);
        restError.setMessage(errorMessage);
        restError.setErrorCode(errorCode);

        return buildResponseEntity(restError);
    }

    private ResponseEntity<Object> buildResponseEntity(RestError restError) {
        return ResponseEntity.status(restError.getStatusCode()).body(restError);
    }
}
