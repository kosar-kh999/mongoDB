package com.example.mongodb.room.repository;

import com.example.mongodb.room.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepo extends MongoRepository<Room, String> {
}
