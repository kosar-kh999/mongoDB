package com.example.mongodb.wallet.model;

import com.example.mongodb.core.base.BaseEntity;
import com.example.mongodb.walletHistory.model.WalletHistory;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Document(collection = "WALLET")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Wallet extends BaseEntity {

    private BigDecimal balance;

    @OneToMany(mappedBy = "wallet")
    private List<WalletHistory> walletHistories;
}
