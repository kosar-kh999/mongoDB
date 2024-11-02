package com.example.mongodb.city.dto;

import com.example.mongodb.core.RequestDTO;
import com.example.mongodb.state.dto.ProvinceRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CityRequestDTO extends RequestDTO {
    private String name;
    private ProvinceRequestDTO province;
}
