package com.example.mongodb.role.model;

import com.example.mongodb.core.BaseEntity;
import com.example.mongodb.user.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Document(collection = "USER")
public class Role extends BaseEntity {

    private String name;
    private String code;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
