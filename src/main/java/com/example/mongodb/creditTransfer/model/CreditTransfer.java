package com.example.mongodb.creditTransfer.model;

import com.example.mongodb.core.base.BaseEntity;
import com.example.mongodb.creditTransfer.enumuration.CreditTransferType;
import com.example.mongodb.user.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Setter
@Getter
@Document(collection = "CREDIT_TRANSFER")
public class CreditTransfer extends BaseEntity {

    private BigDecimal amount;
    private String description;
    private CreditTransferType creditTransferType;

    @ManyToOne
    @JoinColumn(name = "USER-ID")
    private User user;
}
