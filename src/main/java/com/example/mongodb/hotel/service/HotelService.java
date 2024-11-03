package com.example.mongodb.hotel.service;

import com.example.mongodb.city.model.City;
import com.example.mongodb.city.repository.CityRepo;
import com.example.mongodb.core.exception.CustomException;
import com.example.mongodb.hotel.dto.HotelRequestDTO;
import com.example.mongodb.hotel.dto.HotelResponseDTO;
import com.example.mongodb.hotel.mapper.HotelMapper;
import com.example.mongodb.hotel.model.Hotel;
import com.example.mongodb.hotel.record.HotelRecord;
import com.example.mongodb.hotel.repository.HotelRepo;
import com.example.mongodb.room.dto.RoomResponseDTO;
import com.example.mongodb.room.mapper.RoomMapper;
import com.example.mongodb.room.repository.RoomRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepo hotelRepo;
    private final CityRepo cityRepo;
    private final RoomRepo roomRepo;
    private final HotelMapper hotelMapper;
    private final RoomMapper roomMapper;

    public HotelService(HotelRepo hotelRepo,
                        CityRepo cityRepo,
                        RoomRepo roomRepo,
                        HotelMapper hotelMapper,
                        RoomMapper roomMapper) {
        this.hotelRepo = hotelRepo;
        this.cityRepo = cityRepo;
        this.roomRepo = roomRepo;
        this.hotelMapper = hotelMapper;
        this.roomMapper = roomMapper;
    }

    public String save(HotelRecord requestDTO) {
        Optional<City> cityOpt = cityRepo.findById(requestDTO.cityId());
        City city = cityOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", requestDTO.cityId())));
        Hotel hotel = hotelMapper.toEntity(requestDTO);
        hotel.setCity(city);
        return hotelRepo.save(hotel).getId();
    }

    public void update(String id, HotelRequestDTO requestDTO) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        hotelMapper.toEntity(requestDTO, hotel);
        hotelRepo.save(hotel);
    }

    public HotelResponseDTO findById(String id) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return hotelMapper.toDTO(hotel);
    }

    public Page<HotelResponseDTO> findAll(Pageable pageable) {
        Page<Hotel> all = hotelRepo.findAll(pageable);
        return hotelMapper.toDTO(all);
    }

    public void delete(String id) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        hotelRepo.delete(hotel);
    }

    public List<RoomResponseDTO> getRoomsByHotelAndCity(String id) {
        List<Hotel> hotels = hotelRepo.findAllByCityId(id);
        return hotels.stream()
                .flatMap(hotel -> roomRepo.findAllByHotelId(hotel.getId()).stream())
                .map(roomMapper::toDTOForCity)
                .toList();
    }
}
