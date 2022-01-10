package org.example.rbc.exception;

import static org.example.rbc.exception.Reason.C520;

public class CommonIncorrectException extends RbcInternalException {

    public CommonIncorrectException(Exception details) {
        super(C520, details);
    }

}
