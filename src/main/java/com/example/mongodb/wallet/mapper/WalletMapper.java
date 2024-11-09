package com.example.mongodb.wallet.mapper;

import com.example.mongodb.core.base.BaseMapper;
import com.example.mongodb.wallet.dto.WalletRequestDTO;
import com.example.mongodb.wallet.dto.WalletResponseDTO;
import com.example.mongodb.wallet.model.Wallet;
import com.example.mongodb.walletHistory.dto.WalletHistoryResponseDTO;
import com.example.mongodb.walletHistory.mapper.WalletHistoryMapper;
import com.example.mongodb.walletHistory.model.WalletHistory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletMapper implements BaseMapper<Wallet, WalletRequestDTO, WalletResponseDTO> {
    private final WalletHistoryMapper walletHistoryMapper;

    public WalletMapper(WalletHistoryMapper walletHistoryMapper) {
        this.walletHistoryMapper = walletHistoryMapper;
    }

    @Override
    public Wallet toEntity(WalletRequestDTO dto) {
        Wallet wallet = new Wallet();
        toEntity(dto, wallet);
        return wallet;
    }

    @Override
    public void toEntity(WalletRequestDTO dto, Wallet wallet) {
        wallet.setBalance(dto.getBalance());
        if (dto.getWalletHistories() != null) {
            List<WalletHistory> histories = dto.getWalletHistories().stream()
                    .map(walletHistoryMapper::toEntity)
                    .collect(Collectors.toList());
            wallet.setWalletHistories(histories);
        }
    }

    @Override
    public WalletResponseDTO toDTO(Wallet wallet) {
        WalletResponseDTO responseDTO = WalletResponseDTO.builder().build();
        toDTO(wallet, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(Wallet wallet, WalletResponseDTO dto) {
        dto.setBalance(wallet.getBalance());
        if (wallet.getWalletHistories() != null) {
            List<WalletHistoryResponseDTO> walletHistoryResponseDTOS = wallet.getWalletHistories().stream()
                    .map(walletHistoryMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setWalletHistories(walletHistoryResponseDTOS);
        }
        baseField(dto, wallet);
    }
}
