package com.example.mongodb.creditTransfer.repository;

import com.example.mongodb.creditTransfer.model.CreditTransfer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditTransferRepo extends MongoRepository<CreditTransfer, String> {

    @Aggregation(pipeline = {
            "{ '$match': { 'creditTransferType': 'CONFIRMED', 'user._id': { '$eq': ?0 } } }",
            "{ '$lookup': { 'from': 'USER', 'localField': 'user._id', 'foreignField': '_id', 'as': 'userDetails' } }"
    })
    List<CreditTransfer> findConfirmedTransfersForUser(@Param("userId") ObjectId userId);
}



