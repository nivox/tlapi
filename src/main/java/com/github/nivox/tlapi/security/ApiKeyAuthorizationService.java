package com.github.nivox.tlapi.security;

public interface ApiKeyAuthorizationService {

    public boolean authorize(String apiKey);
}
