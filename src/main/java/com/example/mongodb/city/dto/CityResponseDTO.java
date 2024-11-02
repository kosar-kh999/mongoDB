package com.example.mongodb.city.dto;

import com.example.mongodb.core.base.ResponseDTO;
import com.example.mongodb.state.dto.ProvinceResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class CityResponseDTO extends ResponseDTO {
    private String name;
    private ProvinceResponseDTO province;
}
