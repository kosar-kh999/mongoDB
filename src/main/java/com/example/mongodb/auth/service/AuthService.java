package com.example.mongodb.auth.service;

import com.example.mongodb.auth.dto.JwtResponse;
import com.example.mongodb.auth.dto.LoginRequest;
import com.example.mongodb.auth.dto.SignupRequest;
import com.example.mongodb.core.exception.CustomException;
import com.example.mongodb.security.jwt.JwtUtils;
import com.example.mongodb.security.service.UserDetailsImpl;
import com.example.mongodb.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder encoder;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtUtils jwtUtils,
                       UserService userService,
                       PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.encoder = encoder;
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return JwtResponse.builder()
                .token(jwtToken)
                .id(userDetails.id())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    public void registerUser(SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            throw CustomException.builder()
                    .errorCode(HttpStatus.BAD_REQUEST.value())
                    .message("نام کاربری تکراری می باشد.")
                    .build();
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw CustomException.builder()
                    .errorCode(HttpStatus.BAD_REQUEST.value())
                    .message("ایمیل تکراری می باشد.")
                    .build();
        }

        userService.save(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), "USER");
    }
}
