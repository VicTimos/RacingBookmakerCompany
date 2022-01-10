package org.example.rbc.exception;

public class AuthException extends RbcBusinessException {

    public AuthException(Reason r) {
        super(r);
    }
}
