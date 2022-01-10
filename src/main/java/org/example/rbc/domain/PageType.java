package org.example.rbc.domain;

public enum PageType {

    DATA_PAGE("data-page"),
    DATA_ADD("data-add"),
    DATA_EDIT("data-edit"),
    CABINET("cabinet"),
    LOGIN_PAGE("login");

    private String value;

    PageType(String value) { this.value = value; }

    public String getValue() {
        return value;
    }
}
