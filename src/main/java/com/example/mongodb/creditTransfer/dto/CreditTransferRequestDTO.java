package com.example.mongodb.creditTransfer.dto;

import com.example.mongodb.core.base.RequestDTO;
import com.example.mongodb.creditTransfer.enumuration.CreditTransferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CreditTransferRequestDTO extends RequestDTO {
    private BigDecimal amount;
    private String description;
    private CreditTransferType creditTransferType;
}
