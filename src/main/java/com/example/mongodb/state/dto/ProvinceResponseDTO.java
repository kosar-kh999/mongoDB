package com.example.mongodb.state.dto;

import com.example.mongodb.core.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ProvinceResponseDTO extends ResponseDTO {
    private String name;
}
