package com.example.mongodb.city.service;

import com.example.mongodb.city.dto.CityRequestDTO;
import com.example.mongodb.city.dto.CityResponseDTO;
import com.example.mongodb.city.mapper.CityMapper;
import com.example.mongodb.city.model.City;
import com.example.mongodb.city.repository.CityRepo;
import com.example.mongodb.core.exception.CustomException;
import com.example.mongodb.state.model.Province;
import com.example.mongodb.state.repository.ProvinceRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
    private final CityRepo cityRepo;
    private final ProvinceRepo provinceRepo;
    private final CityMapper cityMapper;

    public CityService(CityRepo cityRepo,
                       ProvinceRepo provinceRepo,
                       CityMapper cityMapper) {
        this.cityRepo = cityRepo;
        this.provinceRepo = provinceRepo;
        this.cityMapper = cityMapper;
    }

    public String save(CityRequestDTO requestDTO) {
        City city = cityMapper.toEntity(requestDTO);
        Optional<Province> provinceOpt = provinceRepo.findById(requestDTO.getProvince().getId());
        Province province = provinceOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", requestDTO.getProvince().getId())));
        city.setProvince(province);
        return cityRepo.save(city).getId();
    }

    public void update(String id, CityRequestDTO requestDTO) {
        Optional<City> cityOpt = cityRepo.findById(id);
        City city = cityOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        cityMapper.toEntity(requestDTO, city);
        cityRepo.save(city);
    }

    public CityResponseDTO findById(String id) {
        Optional<City> cityOpt = cityRepo.findById(id);
        City city = cityOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return cityMapper.toDTO(city);
    }

    public Page<CityResponseDTO> findAll(Pageable pageable) {
        Page<City> all = cityRepo.findAll(pageable);
        return cityMapper.toDTO(all);
    }

    public void delete(String id) {
        Optional<City> cityOpt = cityRepo.findById(id);
        City city = cityOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        cityRepo.delete(city);
    }
}

