package com.example.mongodb.room.dto;

import com.example.mongodb.core.RequestDTO;
import com.example.mongodb.hotel.dto.HotelRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO extends RequestDTO {
    private String roomType;
    private Double price;
    private Boolean available;
    private HotelRequestDTO hotel;
}
