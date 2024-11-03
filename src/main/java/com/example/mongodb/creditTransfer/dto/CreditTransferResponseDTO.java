package com.example.mongodb.creditTransfer.dto;

import com.example.mongodb.core.base.ResponseDTO;
import com.example.mongodb.creditTransfer.enumuration.CreditTransferType;
import com.example.mongodb.user.dto.UserResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@SuperBuilder
public class CreditTransferResponseDTO extends ResponseDTO {
    private BigDecimal amount;
    private String description;
    private CreditTransferType creditTransferType;
    private UserResponseDTO user;
}
