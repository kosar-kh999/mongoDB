package com.example.mongodb.wallet.dto;

import com.example.mongodb.core.base.RequestDTO;
import com.example.mongodb.walletHistory.dto.WalletHistoryRequestDTO;
import com.example.mongodb.walletHistory.model.WalletHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequestDTO extends RequestDTO {
    private BigDecimal balance;
    private List<WalletHistoryRequestDTO> walletHistories;
}
