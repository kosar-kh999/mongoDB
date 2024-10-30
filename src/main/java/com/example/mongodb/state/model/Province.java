package com.example.mongodb.state.model;

import com.example.mongodb.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "PROVINCE")
public class Province extends BaseEntity {
    private String name;
}
