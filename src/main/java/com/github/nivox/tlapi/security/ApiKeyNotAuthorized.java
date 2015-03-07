package com.github.nivox.tlapi.security;

public class ApiKeyNotAuthorized extends Exception {

    public ApiKeyNotAuthorized() {}

    public ApiKeyNotAuthorized(String message) {
        super(message);
    }

    public ApiKeyNotAuthorized(String message, Throwable cause) {
        super(message, cause);
    }
}
