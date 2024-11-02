package com.example.mongodb.hotel.dto;

import com.example.mongodb.core.base.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequestDTO extends RequestDTO {
    private String name;
    private String location;
}
