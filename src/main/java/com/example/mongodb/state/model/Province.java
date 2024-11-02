package com.example.mongodb.state.model;

import com.example.mongodb.city.model.City;
import com.example.mongodb.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Setter
@Getter
@Document(collection = "PROVINCE")
public class Province extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    private Set<City> city;
}
