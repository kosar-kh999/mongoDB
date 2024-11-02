package com.example.mongodb.hotel.mapper;

import com.example.mongodb.core.base.BaseMapper;
import com.example.mongodb.hotel.dto.HotelRequestDTO;
import com.example.mongodb.hotel.dto.HotelResponseDTO;
import com.example.mongodb.hotel.model.Hotel;
import com.example.mongodb.hotel.record.HotelRecord;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper implements BaseMapper<Hotel, HotelRequestDTO, HotelResponseDTO> {
    @Override
    public Hotel toEntity(HotelRequestDTO dto) {
        Hotel hotel = new Hotel();
        toEntity(dto, hotel);
        return hotel;
    }

    @Override
    public void toEntity(HotelRequestDTO dto, Hotel hotel) {
        hotel.setName(dto.getName());
        hotel.setLocation(dto.getLocation());
    }

    @Override
    public HotelResponseDTO toDTO(Hotel hotel) {
        HotelResponseDTO responseDTO = HotelResponseDTO.builder().build();
        toDTO(hotel, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(Hotel hotel, HotelResponseDTO dto) {
        dto.setName(hotel.getName());
        dto.setLocation(hotel.getLocation());
        baseField(dto, hotel);
    }

    public Hotel toEntity(HotelRecord record) {
        Hotel hotel = new Hotel();
        hotel.setName(record.name());
        hotel.setLocation(record.location());
        return hotel;
    }
}
