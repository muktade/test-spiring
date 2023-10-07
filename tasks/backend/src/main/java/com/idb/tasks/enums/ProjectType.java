package com.idb.tasks.enums;

public enum ProjectType {

    TEAM(1, "Team Managed"),
    COMPANY(2, "Company Managed");
    private final int id;
    private final String name;

    private ProjectType(int id, String name) {
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
        for (ProjectType dep : values()) {
            if (dep.getId() == id) {
                return dep.getName();
            }
        }
        return "";
    }
}
