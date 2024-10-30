package com.example.mongodb.model;

import com.example.mongodb.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "user")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
}
