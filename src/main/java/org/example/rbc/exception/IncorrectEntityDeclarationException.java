package org.example.rbc.exception;

import static org.example.rbc.exception.Reason.C520;

public class IncorrectEntityDeclarationException extends RbcInternalException {

    public IncorrectEntityDeclarationException(Exception e) {
        super(C520, e);
    }
}
