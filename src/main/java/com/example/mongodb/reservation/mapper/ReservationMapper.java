package com.example.mongodb.reservation.mapper;

import com.example.mongodb.city.mapper.CityMapper;
import com.example.mongodb.core.base.BaseMapper;
import com.example.mongodb.reservation.dto.ReservationDTO;
import com.example.mongodb.reservation.dto.ReservationRequestDTO;
import com.example.mongodb.reservation.dto.ReservationResponseDTO;
import com.example.mongodb.reservation.model.Reservation;
import com.example.mongodb.room.mapper.RoomMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper implements BaseMapper<Reservation, ReservationRequestDTO, ReservationResponseDTO> {
    private final RoomMapper roomMapper;
    private final CityMapper cityMapper;

    public ReservationMapper(RoomMapper roomMapper,
                             CityMapper cityMapper) {
        this.roomMapper = roomMapper;
        this.cityMapper = cityMapper;
    }

    @Override
    public Reservation toEntity(ReservationRequestDTO dto) {
        Reservation reservation = new Reservation();
        toEntity(dto, reservation);
        return reservation;
    }

    @Override
    public void toEntity(ReservationRequestDTO dto, Reservation reservation) {
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());
        reservation.setRoom(roomMapper.toEntity(dto.getRoom()));
    }

    @Override
    public ReservationResponseDTO toDTO(Reservation reservation) {
        ReservationResponseDTO responseDTO = ReservationResponseDTO.builder().build();
        toDTO(reservation, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(Reservation reservation, ReservationResponseDTO dto) {
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setRoom(roomMapper.toDTO(reservation.getRoom()));
        baseField(dto, reservation);
    }

    public ReservationDTO toDTO(Reservation reservation, ReservationDTO dto) {
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setRoom(roomMapper.toDTO(reservation.getRoom()));
        dto.getRoom().getHotel().setCity(cityMapper.toDTO(reservation.getRoom().getHotel().getCity()));
        return dto;
    }
}
