package com.example.mongodb.room.model;

import com.example.mongodb.core.base.BaseEntity;
import com.example.mongodb.hotel.model.Hotel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Document(collection = "ROOM")
public class Room extends BaseEntity {
    private String roomType;
    private Double price;
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
