package org.example.rbc.exception;

public class DomainValidationException extends RbcBusinessException {
    public DomainValidationException() {
        super(Reason.C510);
    }
}
