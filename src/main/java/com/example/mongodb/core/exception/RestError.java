package com.example.mongodb.core.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class RestError {
    private Integer statusCode;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private Integer errorCode;

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

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
