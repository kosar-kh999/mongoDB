package com.example.mongodb.reservation.dto;

import com.example.mongodb.core.base.ResponseDTO;
import com.example.mongodb.room.dto.RoomResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Setter
@Getter
@SuperBuilder
public class ReservationResponseDTO extends ResponseDTO {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private RoomResponseDTO room;
}
