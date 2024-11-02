package com.example.mongodb.hotel.service;

import com.example.mongodb.city.model.City;
import com.example.mongodb.city.repository.CityRepo;
import com.example.mongodb.hotel.dto.HotelRequestDTO;
import com.example.mongodb.hotel.dto.HotelResponseDTO;
import com.example.mongodb.hotel.mapper.HotelMapper;
import com.example.mongodb.hotel.model.Hotel;
import com.example.mongodb.hotel.record.HotelRecord;
import com.example.mongodb.hotel.repository.HotelRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepo hotelRepo;
    private final CityRepo cityRepo;
    private final HotelMapper hotelMapper;

    public HotelService(HotelRepo hotelRepo,
                        CityRepo cityRepo,
                        HotelMapper hotelMapper) {
        this.hotelRepo = hotelRepo;
        this.cityRepo = cityRepo;
        this.hotelMapper = hotelMapper;
    }

    public String save(HotelRecord requestDTO) {
        Optional<City> cityOpt = cityRepo.findById(requestDTO.cityId());
        City city = cityOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", requestDTO.cityId())));
        Hotel hotel = hotelMapper.toEntity(requestDTO);
        hotel.setCity(city);
        return hotelRepo.save(hotel).getId();
    }

    public void update(String id, HotelRequestDTO requestDTO) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        hotelMapper.toEntity(requestDTO, hotel);
        hotelRepo.save(hotel);
    }

    public HotelResponseDTO findById(String id) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return hotelMapper.toDTO(hotel);
    }

    public Page<HotelResponseDTO> findAll(Pageable pageable) {
        Page<Hotel> all = hotelRepo.findAll(pageable);
        return hotelMapper.toDTO(all);
    }

    public void delete(String id) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        hotelRepo.delete(hotel);
    }
}
