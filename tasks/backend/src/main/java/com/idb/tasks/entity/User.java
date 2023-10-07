package com.idb.tasks.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @NotBlank(message = "Please provide valid username")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Please provide valid email")
    @Email(message = "Please provide valid email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(min = 3, message = "Minimum 3 characters required")
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty(message = "Please ")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Role> roles;
}
