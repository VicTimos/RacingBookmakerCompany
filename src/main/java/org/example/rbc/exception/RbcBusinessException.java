package org.example.rbc.exception;

import lombok.Getter;

// Business exceptions are exceptions that occur during runtime and do not depend on particular developing cause.
// In other words, these are external errors. We do not know when in happens. Only while runtime we will know.
// For example, related exceptions are bad host connection or invalid input of user. They are checked, but the compiler
// doesn't see them
public class RbcBusinessException extends RuntimeException {
    @Getter
    private Reason reason;

    public RbcBusinessException(Reason r) {
        reason = r;
    }

    @Override
    public String getMessage() {
        return "(" + reason.name() + ") " + reason.getMessage();
    }

}
