package com.example.mongodb.state.repository;

import com.example.mongodb.state.model.Province;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProvinceRepo extends MongoRepository<Province, String> {
}
