package com.example.mongodb.city.mapper;

import com.example.mongodb.city.dto.CityRequestDTO;
import com.example.mongodb.city.dto.CityResponseDTO;
import com.example.mongodb.city.model.City;
import com.example.mongodb.core.BaseMapper;
import com.example.mongodb.state.mapper.ProvinceMapper;
import org.springframework.stereotype.Component;

@Component
public class CityMapper implements BaseMapper<City, CityRequestDTO, CityResponseDTO> {
    private final ProvinceMapper provinceMapper;

    public CityMapper(ProvinceMapper provinceMapper) {
        this.provinceMapper = provinceMapper;
    }

    @Override
    public City toEntity(CityRequestDTO requestDTO) {
        City city = new City();
        toEntity(requestDTO, city);
        return city;
    }

    @Override
    public void toEntity(CityRequestDTO requestDTO, City city) {
        city.setName(requestDTO.getName());
        city.setProvince(provinceMapper.toEntity(requestDTO.getProvince()));
    }

    @Override
    public CityResponseDTO toDTO(City city) {
        CityResponseDTO responseDTO = CityResponseDTO.builder().build();
        toDTO(city, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(City city, CityResponseDTO responseDTO) {
        responseDTO.setName(city.getName());
        responseDTO.setProvince(provinceMapper.toDTO(city.getProvince()));
        baseField(responseDTO, city);
    }
}
