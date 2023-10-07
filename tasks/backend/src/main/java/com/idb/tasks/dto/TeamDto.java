package com.idb.tasks.dto;

import com.idb.tasks.entity.Team;
import lombok.Data;

@Data
public class TeamDto {

    private Long id;
    private String name;
    private boolean isActive;
    private boolean isApproved;

    public TeamDto() {
    }

    public TeamDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.isActive = team.isActive();
        this.isApproved = team.isApproved();
    }

}
