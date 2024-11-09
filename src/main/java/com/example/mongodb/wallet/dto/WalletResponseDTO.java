package com.example.mongodb.wallet.dto;

import com.example.mongodb.core.base.ResponseDTO;
import com.example.mongodb.walletHistory.dto.WalletHistoryResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@SuperBuilder
public class WalletResponseDTO extends ResponseDTO {
    private BigDecimal balance;
    private List<WalletHistoryResponseDTO> walletHistories;
}
