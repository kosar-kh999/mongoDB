package com.example.mongodb.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class JwtResponse {
    private String token;
    private String type;
    private String id;
    private String username;
    private String email;
    private List<String> roles;
}
