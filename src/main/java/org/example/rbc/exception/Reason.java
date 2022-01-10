package org.example.rbc.exception;

import lombok.Getter;

public enum Reason {

    //Authentication
    C403("Not authenticated"),
    C407("User not found"),
    C408("Password is incorrect"),

    //Internal errors
    C510("Object not valid"),
    C520("Incorrect code logic implementation"),

    //Other
    C0("Unknown error");


    @Getter
    private String message;

    Reason(String msg) {
        message = msg;
    }
}
