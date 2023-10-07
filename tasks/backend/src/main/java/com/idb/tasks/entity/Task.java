package com.idb.tasks.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "task")
public class Task extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Project project;

    @Column(name = "task_type", nullable = false)
    private int taskType;

    @Column(name = "task_status", nullable = false)
    private int taskStatus;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Lob
    @Size(max = 65500, message = "Maximum 65500 characters allowed")
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Employee assignee;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "story_point")
    private Integer storyPoint;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Employee reporter;

    @Lob
    @Size(max = 5242880, message = "Maximum 5mb file allowed")
    @Column(name = "attachment", nullable = false, columnDefinition = "mediumblob")
    private String attachment;

    @Column(name = "link_type")
    private Integer linkType;

    @OneToOne
    private Task linkedIssues;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Employee restrictTo;

}
