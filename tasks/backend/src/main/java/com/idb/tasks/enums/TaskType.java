package com.idb.tasks.enums;

public enum TaskType {

    STORY(1, "Story"),
    BUG(2, "Bug"),
    TASK(3, "Task");
    private final int id;
    private final String name;

    private TaskType(int id, String name) {
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
        for (TaskType dep : values()) {
            if (dep.getId() == id) {
                return dep.getName();
            }
        }
        return "";
    }
}
