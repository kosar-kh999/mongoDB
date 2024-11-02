package com.example.mongodb.room.controller;

import com.example.mongodb.room.dto.RoomRequestDTO;
import com.example.mongodb.room.dto.RoomResponseDTO;
import com.example.mongodb.room.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping(value = "/room")
    public ResponseEntity<String> add(@RequestBody RoomRequestDTO requestDTO) {
        String savedId = roomService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/room/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody RoomRequestDTO requestDTO) {
        roomService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/room/{id}")
    public ResponseEntity<RoomResponseDTO> getById(@PathVariable String id) {
        RoomResponseDTO responseDTO = roomService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/room")
    public ResponseEntity<Page<RoomResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<RoomResponseDTO> page = roomService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/room/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/room/hotel/{id}")
    public ResponseEntity<List<RoomResponseDTO>> getRoomsByHotel(@PathVariable String id) {
        List<RoomResponseDTO> rooms = roomService.getRoomsByHotel(id);
        return ResponseEntity.ok().body(rooms);
    }
}
