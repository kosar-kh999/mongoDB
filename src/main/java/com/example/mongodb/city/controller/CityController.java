package com.example.mongodb.city.controller;

import com.example.mongodb.city.dto.CityRequestDTO;
import com.example.mongodb.city.dto.CityResponseDTO;
import com.example.mongodb.city.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping(value = "/city")
    public ResponseEntity<String> add(@RequestBody CityRequestDTO requestDTO) {
        String savedId = cityService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/city/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody CityRequestDTO requestDTO) {
        cityService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/city/{id}")
    public ResponseEntity<CityResponseDTO> getById(@PathVariable String id) {
        CityResponseDTO responseDTO = cityService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/city")
    public ResponseEntity<Page<CityResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<CityResponseDTO> page = cityService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/city/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
