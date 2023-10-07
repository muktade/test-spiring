package com.idb.tasks.dto;

import com.idb.tasks.entity.BaseEntity;
import com.idb.tasks.entity.Sprint;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SprintDto extends BaseEntity {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String name;
    private boolean isActive;
    private boolean isApproved;

    public String getName() {
        return start.toString() + " - " + end.toString();
    }

    public SprintDto() {
    }

    public SprintDto(Sprint sprint) {
        this.id = sprint.getId();
        this.start = sprint.getStart();
        this.end = sprint.getEnd();
        this.name = sprint.getName();
        this.isActive = sprint.isActive();
        this.isApproved = sprint.isApproved();
    }

}
