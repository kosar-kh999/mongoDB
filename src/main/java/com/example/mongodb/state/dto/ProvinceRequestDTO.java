package com.example.mongodb.state.dto;

import com.example.mongodb.core.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceRequestDTO extends RequestDTO {
    private String name;
}
