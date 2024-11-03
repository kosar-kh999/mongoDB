package com.example.mongodb.creditTransfer.repository;

import com.example.mongodb.creditTransfer.model.CreditTransfer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditTransferRepo extends MongoRepository<CreditTransfer, String> {
}
