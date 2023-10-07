package com.idb.tasks.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    @Pattern(regexp = "[A-Z]+[A-Z0-9]+", message = "Please provide valid Employee ID. i.e. IDB1209345")
    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Pattern(regexp = "[a-zA-Z0-9.]+", message = "Please provide valid name")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Min(value = 1, message = "Please provide designation")
    @Column(name = "designation", nullable = false)
    private Integer designation;

    @Column(name = "organization", nullable = false)
    private String organization;

    @Column(name = "department", nullable = false)
    private Integer department;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "employee_team",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teams;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Employee supervisor;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private User user;
}
