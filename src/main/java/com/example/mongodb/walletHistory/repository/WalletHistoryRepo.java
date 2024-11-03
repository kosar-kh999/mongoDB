package com.example.mongodb.walletHistory.repository;

import com.example.mongodb.walletHistory.model.WalletHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletHistoryRepo extends MongoRepository<WalletHistory, String> {
}
