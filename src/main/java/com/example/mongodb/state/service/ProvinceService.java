package com.example.mongodb.state.service;

import com.example.mongodb.core.exception.CustomException;
import com.example.mongodb.state.dto.ProvinceRequestDTO;
import com.example.mongodb.state.dto.ProvinceResponseDTO;
import com.example.mongodb.state.mapper.ProvinceMapper;
import com.example.mongodb.state.model.Province;
import com.example.mongodb.state.repository.ProvinceRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService {
    private final ProvinceRepo provinceRepo;
    private final ProvinceMapper provinceMapper;

    public ProvinceService(ProvinceRepo provinceRepo,
                           ProvinceMapper provinceMapper) {
        this.provinceRepo = provinceRepo;
        this.provinceMapper = provinceMapper;
    }

    public String save(ProvinceRequestDTO requestDTO) {
        Province province = provinceMapper.toEntity(requestDTO);
        return provinceRepo.save(province).getId();
    }

    public void update(String id, ProvinceRequestDTO requestDTO) {
        Optional<Province> provinceOpt = provinceRepo.findById(id);
        Province province = provinceOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        provinceMapper.toEntity(requestDTO, province);
        provinceRepo.save(province);
    }

    public ProvinceResponseDTO findById(String id) {
        Optional<Province> provinceOpt = provinceRepo.findById(id);
        Province province = provinceOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return provinceMapper.toDTO(province);
    }

    public Page<ProvinceResponseDTO> findAll(Pageable pageable) {
        Page<Province> all = provinceRepo.findAll(pageable);
        return provinceMapper.toDTO(all);
    }

    public void delete(String id) {
        Optional<Province> provinceOpt = provinceRepo.findById(id);
        Province province = provinceOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        provinceRepo.delete(province);
    }
}
