package com.example.mongodb.user.controller;

import com.example.mongodb.user.dto.UserRequestDTO;
import com.example.mongodb.user.dto.UserResponseDTO;
import com.example.mongodb.user.record.ResetPasswordRecord;
import com.example.mongodb.user.record.UserRecord;
import com.example.mongodb.user.service.UserService;
import com.example.mongodb.wallet.dto.WalletResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public ResponseEntity<String> add(@Valid @RequestBody UserRequestDTO requestDTO) {
        String savedId = userService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserRequestDTO requestDTO) {
        userService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable String id) {
        UserResponseDTO responseDTO = userService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<Page<UserResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<UserResponseDTO> page = userService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/user/role")
    public ResponseEntity<Void> assignRoleForUser(@RequestBody UserRecord requestDTO) {
        userService.assignRoleForUser(requestDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/user/reset/password")
    public ResponseEntity<Void> resetPasswordOfUser(@RequestBody ResetPasswordRecord requestDTO) {
        userService.resetPasswordOfUser(requestDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/user/wallet")
    public ResponseEntity<Void> updateUser() {
        userService.updateWalletOfUser();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/user/wallet-history/{id}")
    public ResponseEntity<List<WalletResponseDTO>> getUserWalletHistory(@PathVariable String id) {
        List<WalletResponseDTO> responseDTOS = userService.getUserWalletHistory(id);
        return ResponseEntity.ok(responseDTOS);
    }
}
