package com.example.mongodb.creditTransfer.controller;

import com.example.mongodb.creditTransfer.dto.CreditTransferRequestDTO;
import com.example.mongodb.creditTransfer.dto.CreditTransferResponseDTO;
import com.example.mongodb.creditTransfer.record.AcceptCreditRecord;
import com.example.mongodb.creditTransfer.record.CreditFilterRecord;
import com.example.mongodb.creditTransfer.record.CreditTransferRecord;
import com.example.mongodb.creditTransfer.service.CreditTransferService;
import com.example.mongodb.wallet.dto.WalletResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreditTransferController {
    private final CreditTransferService creditTransferService;

    public CreditTransferController(CreditTransferService creditTransferService) {
        this.creditTransferService = creditTransferService;
    }

    @PostMapping(value = "/credit-transfer")
    public ResponseEntity<String> add(@RequestBody CreditTransferRequestDTO requestDTO) {
        String savedId = creditTransferService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/credit-transfer/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody CreditTransferRequestDTO requestDTO) {
        creditTransferService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/credit-transfer/{id}")
    public ResponseEntity<CreditTransferResponseDTO> getById(@PathVariable String id) {
        CreditTransferResponseDTO responseDTO = creditTransferService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/credit-transfer")
    public ResponseEntity<Page<CreditTransferResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<CreditTransferResponseDTO> page = creditTransferService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/credit-transfer/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        creditTransferService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/credit-transfer/user")
    public ResponseEntity<CreditTransferResponseDTO> AddCreditForUser(@RequestBody CreditTransferRecord requestDTO) {
        CreditTransferResponseDTO responseDTO = creditTransferService.AddCreditForUser(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value = "/credit-transfer/accepted")
    public ResponseEntity<WalletResponseDTO> increaseCredit(@RequestBody AcceptCreditRecord requestDTO) {
        WalletResponseDTO walletResponseDTO = creditTransferService.increaseCredit(requestDTO);
        return ResponseEntity.ok(walletResponseDTO);
    }

    @PostMapping(value = "/credit-transfer/rejected")
    public ResponseEntity<Void> rejectCreditTransfer(@RequestBody AcceptCreditRecord requestDTO) {
        creditTransferService.rejectCreditTransfer(requestDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/credit-transfer/filter")
    public ResponseEntity<List<CreditTransferResponseDTO>> findTransfersForUser(@RequestBody CreditFilterRecord requestDTO) {
        List<CreditTransferResponseDTO> responseDTOS = creditTransferService.findTransfersForUser(requestDTO);
        return ResponseEntity.ok(responseDTOS);
    }
}
