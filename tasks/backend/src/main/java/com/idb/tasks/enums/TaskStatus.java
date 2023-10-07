package com.idb.tasks.enums;

public enum TaskStatus {

    NOT_READY(1, "Not Ready"),
    TODO(2, "To do"),
    IN_PROGRESS(3, "In Progress"),
    DONE(4, "Done"),
    CLOSED(5, "Closed"),
    BLOCKED(6, "Blocked");
    private final int id;
    private final String name;

    private TaskStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getNameById(int id) {
        for (TaskStatus dep : values()) {
            if (dep.getId() == id) {
                return dep.getName();
            }
        }
        return "";
    }
}
