package com.idb.tasks.enums;

public enum Department {

    MARKETING(1, "Marketing"),
    ACCOUNTING(2, "Accounting"),
    HRM(3, "Human Resource Management"),
    PRODUCTION(4, "Production");
    private final int id;
    private final String name;

    private Department(int id, String name) {
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
        for (Department dep : values()) {
            if (dep.getId() == id) {
                return dep.getName();
            }
        }
        return "";
    }
}
