package com.example.mongodb.room.service;

import com.example.mongodb.core.exception.CustomException;
import com.example.mongodb.hotel.model.Hotel;
import com.example.mongodb.hotel.repository.HotelRepo;
import com.example.mongodb.room.dto.RoomRequestDTO;
import com.example.mongodb.room.dto.RoomResponseDTO;
import com.example.mongodb.room.mapper.RoomMapper;
import com.example.mongodb.room.model.Room;
import com.example.mongodb.room.repository.RoomRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepo roomRepo;
    private final HotelRepo hotelRepo;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepo roomRepo,
                       HotelRepo hotelRepo,
                       RoomMapper roomMapper) {
        this.roomRepo = roomRepo;
        this.hotelRepo = hotelRepo;
        this.roomMapper = roomMapper;
    }


    public String save(RoomRequestDTO requestDTO) {
        Room room = roomMapper.toEntity(requestDTO);
        Optional<Hotel> hotelOpt = hotelRepo.findById(requestDTO.getHotel().getId());
        Hotel hotel = hotelOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", requestDTO.getHotel().getId())));
        room.setHotel(hotel);
        return roomRepo.save(room).getId();
    }

    public void update(String id, RoomRequestDTO requestDTO) {
        Optional<Room> roomOpt = roomRepo.findById(id);
        Room room = roomOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        roomMapper.toEntity(requestDTO, room);
        roomRepo.save(room);
    }

    public RoomResponseDTO findById(String id) {
        Optional<Room> roomOpt = roomRepo.findById(id);
        Room room = roomOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return roomMapper.toDTO(room);
    }

    public Page<RoomResponseDTO> findAll(Pageable pageable) {
        Page<Room> all = roomRepo.findAll(pageable);
        return roomMapper.toDTO(all);
    }

    public void delete(String id) {
        Optional<Room> roomOpt = roomRepo.findById(id);
        Room room = roomOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        roomRepo.delete(room);
    }

    public List<RoomResponseDTO> getRoomsByHotel(String id) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return roomRepo.findAll().stream()
                .filter(room -> room.getHotel().getId().equals(hotel.getId()))
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }
}
