package com.example.mongodb.user.model;

import com.example.mongodb.core.base.BaseEntity;
import com.example.mongodb.creditTransfer.model.CreditTransfer;
import com.example.mongodb.role.model.Role;
import com.example.mongodb.wallet.model.Wallet;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Document(collection = "USER")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    @NotNull(message = "Username cannot be null")
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<CreditTransfer> creditTransfers;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Wallet wallet;

    public User() {
        this.roles = new HashSet<>();
    }
}
