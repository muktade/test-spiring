package com.idb.tasks.dto;

import com.idb.tasks.entity.Employee;
import com.idb.tasks.entity.Project;
import com.idb.tasks.entity.Team;
import com.idb.tasks.enums.Department;
import com.idb.tasks.enums.Designation;
import lombok.Data;

import java.util.Set;

@Data
public class EmployeeDto {
    private Long id;
    private String employeeId;
    private String name;
    private String phone;
    private String designation;
    private String organization;
    private String department;
    private Set<Project> projects;
    private Set<Team> teams;
    private EmployeeDto supervisor;
    private boolean isActive;
    private boolean isApproved;

    public EmployeeDto() {

    }

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.employeeId = employee.getEmployeeId();
        this.name = employee.getName();
        this.phone = employee.getPhone();
        this.designation = Designation.getNameById(employee.getDesignation());
        this.organization = employee.getOrganization();
        this.department = Department.getNameById(employee.getDepartment());
        this.projects = employee.getProjects();
        this.teams = employee.getTeams();
        this.supervisor = employee.getSupervisor() != null ? new EmployeeDto(employee.getSupervisor()) : null;
        this.isActive = employee.isActive();
        this.isApproved = employee.isApproved();
    }
}
