package com.example.mongodb.room.dto;

import com.example.mongodb.core.ResponseDTO;
import com.example.mongodb.hotel.dto.HotelResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class RoomResponseDTO extends ResponseDTO {
    private String roomType;
    private Double price;
    private Boolean available;
    private HotelResponseDTO hotel;
}
