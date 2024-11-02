package com.example.mongodb.reservation.repository;

import com.example.mongodb.reservation.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepo extends MongoRepository<Reservation, String> {
}
