package com.example.mongodb.city.model;

import com.example.mongodb.core.base.BaseEntity;
import com.example.mongodb.state.model.Province;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Document(collection = "CITY")
public class City extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID")
    private Province province;
}
