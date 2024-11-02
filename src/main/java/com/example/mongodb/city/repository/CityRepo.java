package com.example.mongodb.city.repository;

import com.example.mongodb.city.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityRepo extends MongoRepository<City, String> {
}
