package com.example.mongodb.room.repository;

import com.example.mongodb.room.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepo extends MongoRepository<Room, String> {
    List<Room> findAllByHotelId(String hotelId);
}
