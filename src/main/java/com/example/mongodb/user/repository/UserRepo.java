package com.example.mongodb.user.repository;

import com.example.mongodb.user.model.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    @Aggregation(pipeline = {
            "{ '$match': { '_id': ?0 } }",
            "{ '$lookup': { 'from': 'WALLET', 'localField': 'wallet._id', 'foreignField': '_id', 'as': 'wallet' } }",
            "{ '$unwind': { 'path': '$wallet'} }"
    })
    List<User> getUserWalletHistory(String userId);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
