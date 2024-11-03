package com.example.mongodb.walletHistory.dto;

import com.example.mongodb.core.base.RequestDTO;
import com.example.mongodb.walletHistory.enumuration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class WalletHistoryRequestDTO extends RequestDTO {
    private String description;
    private BigDecimal credit;
    private TransactionType transactionType;
}
