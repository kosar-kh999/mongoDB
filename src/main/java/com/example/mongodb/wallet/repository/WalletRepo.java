package com.example.mongodb.wallet.repository;

import com.example.mongodb.wallet.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepo extends MongoRepository<Wallet, String> {
}
