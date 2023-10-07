package com.idb.tasks.dto;

import com.idb.tasks.entity.Role;
import lombok.Data;

@Data
public class RoleDto {

    private Long id;
    private String name;
    private boolean isActive;
    private boolean isApproved;

    public RoleDto() {
    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.isActive = role.isActive();
        this.isApproved = role.isApproved();
    }
}
