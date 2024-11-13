package com.example.mongodb.security.jwt;

import com.example.mongodb.core.exception.RestError;
import com.example.mongodb.core.util.SecurityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private final ObjectMapper mapper;

    public AuthEntryPointJwt() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        this.mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm a z"));
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String referenceId = "HOTEL_" + UUID.randomUUID();
        String url = request.getMethod() + " " + request.getRequestURI();
        String ip = SecurityUtils.getIp(request);
        log.error("Unauthorized error referenceId: {} {} {}", referenceId, ip, url);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        RestError apiError = RestError.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message("عدم دسترسی")
                .errors(Collections.singletonList(authException.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();

        mapper.writeValue(response.getOutputStream(), apiError);
    }
}
