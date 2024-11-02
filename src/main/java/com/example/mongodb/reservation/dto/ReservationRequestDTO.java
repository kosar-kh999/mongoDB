package com.example.mongodb.reservation.dto;

import com.example.mongodb.core.base.RequestDTO;
import com.example.mongodb.room.dto.RoomRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO extends RequestDTO {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private RoomRequestDTO room;
}
