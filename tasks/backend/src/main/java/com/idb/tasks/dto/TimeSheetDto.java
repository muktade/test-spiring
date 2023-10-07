package com.idb.tasks.dto;

import com.idb.tasks.entity.Employee;
import com.idb.tasks.entity.Project;
import com.idb.tasks.entity.Task;
import com.idb.tasks.entity.TimeSheet;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeSheetDto {
    private Long id;
    private Employee employee;
    private Project project;
    private Task task;
    private LocalDateTime start;
    private LocalDateTime end;
    private double timeSpent;
    private double timeApproved;
    private String description;
    private boolean isActive;
    private boolean isApproved;

    public TimeSheetDto() {
    }

    public TimeSheetDto(TimeSheet timeSheet) {
        this.id = timeSheet.getId();
        this.employee = timeSheet.getEmployee();
        this.project = timeSheet.getProject();
        this.task = timeSheet.getTask();
        this.start = timeSheet.getStart();
        this.end = timeSheet.getEnd();
        this.timeSpent = timeSheet.getTimeSpent();
        this.timeApproved = timeSheet.getTimeApproved();
        this.description = timeSheet.getDescription();
        this.isActive = timeSheet.isActive();
        this.isApproved = timeSheet.isApproved();
    }

}
