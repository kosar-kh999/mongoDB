package com.example.mongodb.wallet.model;

import com.example.mongodb.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Setter
@Getter
@Document(collection = "WALLET")
public class Wallet extends BaseEntity {

    private BigDecimal balance;
}
