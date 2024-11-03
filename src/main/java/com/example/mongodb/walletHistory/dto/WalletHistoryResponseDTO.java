package com.example.mongodb.walletHistory.dto;

import com.example.mongodb.core.base.ResponseDTO;
import com.example.mongodb.walletHistory.enumuration.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@SuperBuilder
public class WalletHistoryResponseDTO extends ResponseDTO {
    private String description;
    private BigDecimal credit;
    private TransactionType transactionType;
}
