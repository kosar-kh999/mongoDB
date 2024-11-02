package com.example.mongodb.wallet.dto;

import com.example.mongodb.core.base.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@SuperBuilder
public class WalletResponseDTO extends ResponseDTO {
    private BigDecimal balance;
}
