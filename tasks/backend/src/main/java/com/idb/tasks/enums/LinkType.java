package com.idb.tasks.enums;

public enum LinkType {

    BLOCK(1, "Blocks"),
    CLONE(2, "Clones"),
    RELATE(2, "Relates to"),
    DUPLICATE(3, "Duplicate");
    private final int id;
    private final String name;

    private LinkType(int id, String name) {
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
        for (LinkType dep : values()) {
            if (dep.getId() == id) {
                return dep.getName();
            }
        }
        return "";
    }
}
