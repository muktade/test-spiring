package com.idb.tasks.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "project")
public class Project extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "project_id", nullable = false, unique = true)
    private String projectId;

    @Column(name = "type", nullable = false)
    private String type;

}
