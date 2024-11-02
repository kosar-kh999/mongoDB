package com.example.mongodb.hotel.dto;

import com.example.mongodb.city.dto.CityResponseDTO;
import com.example.mongodb.core.base.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class HotelResponseDTO extends ResponseDTO {
    private String name;
    private String location;
    private CityResponseDTO city;
}
