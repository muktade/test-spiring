package com.idb.tasks.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sprint")
public class Sprint extends BaseEntity {

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Column(name = "end", nullable = false)
    private LocalDateTime end;

    @Transient
    private String name;

    public String getName() {
        return start.toString() + " - " + end.toString();
    }

}
