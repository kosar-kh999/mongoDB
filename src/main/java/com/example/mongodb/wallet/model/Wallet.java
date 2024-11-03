package com.example.mongodb.wallet.model;

import com.example.mongodb.core.base.BaseEntity;
import com.example.mongodb.walletHistory.model.WalletHistory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document(collection = "WALLET")
public class Wallet extends BaseEntity {

    private BigDecimal balance;

    @OneToMany(mappedBy = "wallet")
    private List<WalletHistory> walletHistories = new ArrayList<>();
}
