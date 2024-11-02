package com.example.mongodb.wallet.controller;

import com.example.mongodb.wallet.dto.WalletRequestDTO;
import com.example.mongodb.wallet.dto.WalletResponseDTO;
import com.example.mongodb.wallet.service.WalletService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping(value = "/wallet")
    public ResponseEntity<String> add(@RequestBody WalletRequestDTO requestDTO) {
        String savedId = walletService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/wallet/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody WalletRequestDTO requestDTO) {
        walletService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/wallet/{id}")
    public ResponseEntity<WalletResponseDTO> getById(@PathVariable String id) {
        WalletResponseDTO responseDTO = walletService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/wallet")
    public ResponseEntity<Page<WalletResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<WalletResponseDTO> page = walletService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/wallet/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        walletService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
