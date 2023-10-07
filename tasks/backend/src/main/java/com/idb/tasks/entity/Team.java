package com.idb.tasks.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "team")
public class Team extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
