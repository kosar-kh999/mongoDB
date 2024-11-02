package com.example.mongodb.reservation.model;

import com.example.mongodb.core.base.BaseEntity;
import com.example.mongodb.room.model.Room;
import com.example.mongodb.user.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Setter
@Getter
@Document(collection = "RESERVATION")
public class Reservation extends BaseEntity {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
