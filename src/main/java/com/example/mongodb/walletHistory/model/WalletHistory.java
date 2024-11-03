package com.example.mongodb.walletHistory.model;

import com.example.mongodb.core.base.BaseEntity;
import com.example.mongodb.walletHistory.enumuration.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Document(collection = "WALLET_HISTORY")
public class WalletHistory extends BaseEntity {

    private String description;
    private BigDecimal credit;
    private TransactionType transactionType;

    @CreatedDate
    private LocalDateTime transactionDate;
}
