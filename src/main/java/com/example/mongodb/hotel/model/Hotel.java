package com.example.mongodb.hotel.model;

import com.example.mongodb.city.model.City;
import com.example.mongodb.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Document(collection = "HOTEL")
public class Hotel extends BaseEntity {

    private String name;
    private String location;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
