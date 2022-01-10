package org.example.rbc.domain;

public enum RoleType {

    ROLE_ANONYMOUS("Anonymous"),
    ROLE_ADMIN("Administrator"),
    ROLE_BOOKMAKER("Bookmaker"),
    ROLE_CLIENT("Client");

    private String name;

    RoleType(String name) {
        this.name = name;
    }
}
