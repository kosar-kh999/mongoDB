package com.example.mongodb.hotel.repository;

import com.example.mongodb.hotel.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HotelRepo extends MongoRepository<Hotel, String> {
    List<Hotel> findAllByCityId(String cityId);
}
