package com.example.mongodb.role.repository;

import com.example.mongodb.role.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepo extends MongoRepository<Role, String> {
}
