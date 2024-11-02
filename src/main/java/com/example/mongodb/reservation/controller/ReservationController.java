package com.example.mongodb.reservation.controller;

import com.example.mongodb.reservation.dto.ReservationDTO;
import com.example.mongodb.reservation.dto.ReservationRequestDTO;
import com.example.mongodb.reservation.dto.ReservationResponseDTO;
import com.example.mongodb.reservation.record.ReservationRecord;
import com.example.mongodb.reservation.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping(value = "/reservation")
    public ResponseEntity<String> add(@RequestBody ReservationRequestDTO requestDTO) {
        String savedId = reservationService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/reservation/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody ReservationRequestDTO requestDTO) {
        reservationService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/reservation/{id}")
    public ResponseEntity<ReservationResponseDTO> getById(@PathVariable String id) {
        ReservationResponseDTO responseDTO = reservationService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/reservation")
    public ResponseEntity<Page<ReservationResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<ReservationResponseDTO> page = reservationService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/reservation/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/reservation/room")
    public ResponseEntity<ReservationDTO> reserveRoom(@RequestBody ReservationRecord requestDTO) {
        ReservationDTO responseDTO = reservationService.reserveRoom(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
