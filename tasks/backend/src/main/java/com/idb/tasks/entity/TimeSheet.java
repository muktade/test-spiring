package com.idb.tasks.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "time_sheet")
public class TimeSheet extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Task task;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Column(name = "end", nullable = false)
    private LocalDateTime end;

    @Column(name = "time_spent", nullable = false)
    private double timeSpent;

    @Column(name = "time_approved", nullable = false)
    private double timeApproved;

    @Lob
    @Size(max = 65500, message = "Maximum 65500 characters allowed")
    @Column(name = "description", nullable = false)
    private String description;


}
