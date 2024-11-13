package com.example.mongodb.core.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Getter
@Setter
public class RestError {
    @Getter
    private Integer statusCode;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private Integer errorCode;
    private HttpStatus status;
    private List<String> errors;

    private RestError() {
        timestamp = LocalDateTime.now();
    }

    RestError(Integer statusCode) {
        this();
        this.statusCode = statusCode;
    }

    RestError(Integer statusCode, Throwable ex) {
        this();
        this.statusCode = statusCode;
        this.message = "error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    RestError(Integer statusCode, String message, Throwable ex) {
        this();
        this.statusCode = statusCode;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
