package com.idb.tasks.dto;

import com.idb.tasks.entity.Employee;
import com.idb.tasks.entity.Project;
import com.idb.tasks.entity.Task;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private Project project;
    private int taskType;
    private int taskStatus;
    private String summary;
    private String description;
    private Employee assignee;
    private String label;
    private Integer storyPoint;
    private Employee reporter;
    private String attachment;
    private Integer linkType;
    private TaskDto linkedIssues;
    private Employee restrictTo;
    private boolean isActive;
    private boolean isApproved;

    public TaskDto() {
    }

    public TaskDto(Task task) {
        this.id = task.getId();
        this.project = task.getProject();
        this.taskType = task.getTaskType();
        this.taskStatus = task.getTaskStatus();
        this.summary = task.getSummary();
        this.description = task.getDescription();
        this.assignee = task.getAssignee();
        this.label = task.getLabel();
        this.storyPoint = task.getStoryPoint();
        this.reporter = task.getReporter();
        this.attachment = task.getAttachment();
        this.linkType = task.getLinkType();
        this.linkedIssues = task.getLinkedIssues() != null ? new TaskDto(task.getLinkedIssues()) : null;
        this.restrictTo = task.getRestrictTo();
        this.isActive = task.isActive();
        this.isApproved = task.isApproved();
    }
}
