package com.example.mongodb.hotel.repository;

import com.example.mongodb.hotel.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepo extends MongoRepository<Hotel, String> {
}
