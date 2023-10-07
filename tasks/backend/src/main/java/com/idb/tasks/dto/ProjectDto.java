package com.idb.tasks.dto;

import com.idb.tasks.entity.Project;
import lombok.Data;

@Data
public class ProjectDto {

    private Long id;
    private String name;
    private String projectId;
    private String type;
    private boolean isActive;
    private boolean isApproved;

    public ProjectDto() {
    }

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.projectId = project.getProjectId();
        this.type = project.getType();
        this.isActive = project.isActive();
        this.isApproved = project.isApproved();
    }

}
