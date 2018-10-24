package com.pt1002.common.exceptions;

public class RemoteServerException extends Exception {

    public RemoteServerException(String message) {
        super(message);
    }

    public RemoteServerException() {
    }

    public RemoteServerException(Throwable cause) {
        super(cause);
    }
}
