package org.example.rbc.exception;

import lombok.Getter;

// Internal exceptions are exceptions that occur by the fault of the development process. They are checked and compiler
// see them. These errors are occurred only when developer missed something while development and do not relate to
// business logic of the application. Such exceptions are incorrect usage of Spring beans injection, for example, that
// causes BeanNotFoundException, if bean not in the Spring context
public class RbcInternalException extends Exception {

    @Getter
    private Reason reason;

    public RbcInternalException(Reason r, Exception details) {
        reason = r;
    }

    @Override
    public String getMessage() {
        return "(" + reason.name() + ") " + reason.getMessage();
    }

}
