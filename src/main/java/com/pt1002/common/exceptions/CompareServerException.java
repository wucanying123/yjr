package com.pt1002.common.exceptions;

public class CompareServerException extends Exception {

    public CompareServerException(Throwable cause) {
        super(cause);
    }

    public CompareServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompareServerException(String message) {
        super(message);
    }

    public CompareServerException() {
    }
}
