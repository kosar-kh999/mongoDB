package com.example.mongodb.walletHistory.service;

import com.example.mongodb.walletHistory.dto.WalletHistoryRequestDTO;
import com.example.mongodb.walletHistory.dto.WalletHistoryResponseDTO;
import com.example.mongodb.walletHistory.mapper.WalletHistoryMapper;
import com.example.mongodb.walletHistory.model.WalletHistory;
import com.example.mongodb.walletHistory.repository.WalletHistoryRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletHistoryService {
    private final WalletHistoryRepo walletHistoryRepo;
    private final WalletHistoryMapper walletHistoryMapper;

    public WalletHistoryService(WalletHistoryRepo walletHistoryRepo,
                                WalletHistoryMapper walletHistoryMapper) {
        this.walletHistoryRepo = walletHistoryRepo;
        this.walletHistoryMapper = walletHistoryMapper;
    }

    public String save(WalletHistoryRequestDTO requestDTO) {
        WalletHistory walletHistory = walletHistoryMapper.toEntity(requestDTO);
        return walletHistoryRepo.save(walletHistory).getId();
    }

    public void update(String id, WalletHistoryRequestDTO requestDTO) {
        Optional<WalletHistory> walletHistoryOpt = walletHistoryRepo.findById(id);
        WalletHistory walletHistory = walletHistoryOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        walletHistoryMapper.toEntity(requestDTO, walletHistory);
        walletHistoryRepo.save(walletHistory);
    }

    public WalletHistoryResponseDTO findById(String id) {
        Optional<WalletHistory> walletHistoryOpt = walletHistoryRepo.findById(id);
        WalletHistory walletHistory = walletHistoryOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return walletHistoryMapper.toDTO(walletHistory);
    }

    public Page<WalletHistoryResponseDTO> findAll(Pageable pageable) {
        Page<WalletHistory> all = walletHistoryRepo.findAll(pageable);
        return walletHistoryMapper.toDTO(all);
    }

    public void delete(String id) {
        Optional<WalletHistory> walletHistoryOpt = walletHistoryRepo.findById(id);
        WalletHistory walletHistory = walletHistoryOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        walletHistoryRepo.delete(walletHistory);
    }
}
