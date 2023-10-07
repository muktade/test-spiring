package com.idb.tasks.enums;

public enum Designation {

    MARKETER(1, "Marketer"),
    ACCOUNTANT(2, "Accountant"),
    HR(3, "Human Resource Manager"),
    SOFTWARE_ENGINEER(4, "Software Engineer");
    private final int id;
    private final String name;

    private Designation(int id, String name) {
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
        for (Designation dep : values()) {
            if (dep.getId() == id) {
                return dep.getName();
            }
        }
        return "";
    }
}
