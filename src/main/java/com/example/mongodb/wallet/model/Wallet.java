package com.example.mongodb.wallet.model;

import com.example.mongodb.core.base.BaseEntity;
import com.example.mongodb.user.model.User;
import com.example.mongodb.walletHistory.model.WalletHistory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document(collection = "WALLET")
public class Wallet extends BaseEntity {

    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletHistory> walletHistories = new ArrayList<>();
}
