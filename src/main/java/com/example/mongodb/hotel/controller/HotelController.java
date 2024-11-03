package com.example.mongodb.hotel.controller;

import com.example.mongodb.hotel.dto.HotelRequestDTO;
import com.example.mongodb.hotel.dto.HotelResponseDTO;
import com.example.mongodb.hotel.record.HotelRecord;
import com.example.mongodb.hotel.service.HotelService;
import com.example.mongodb.room.dto.RoomResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(value = "/hotel")
    public ResponseEntity<String> add(@RequestBody HotelRecord requestDTO) {
        String savedId = hotelService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/hotel/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody HotelRequestDTO requestDTO) {
        hotelService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/hotel/{id}")
    public ResponseEntity<HotelResponseDTO> getById(@PathVariable String id) {
        HotelResponseDTO responseDTO = hotelService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/hotel")
    public ResponseEntity<Page<HotelResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<HotelResponseDTO> page = hotelService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/hotel/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        hotelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hotel/room/city/{id}")
    public ResponseEntity<List<RoomResponseDTO>> getRoomsByHotelAndCity(@PathVariable String id) {
        List<RoomResponseDTO> rooms = hotelService.getRoomsByHotelAndCity(id);
        return ResponseEntity.ok().body(rooms);
    }
}
