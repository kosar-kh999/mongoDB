package com.example.mongodb.creditTransfer.repository;

import com.example.mongodb.creditTransfer.enumuration.CreditTransferType;
import com.example.mongodb.creditTransfer.model.CreditTransfer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CreditTransferRepo extends MongoRepository<CreditTransfer, String> {

    @Query("{ 'user._id': ?0, 'creditTransferType': ?1 }")
    List<CreditTransfer> findByUserIdAndType(String userId, CreditTransferType creditTransferType);
}

