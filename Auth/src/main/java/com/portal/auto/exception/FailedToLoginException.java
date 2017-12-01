package com.portal.auto.exception;

import static java.lang.String.format;

@SuppressWarnings("serial")
public class FailedToLoginException extends RuntimeException {
    public FailedToLoginException(String username) {
        super(format("Failed to login with username %s", username));
    }
}
