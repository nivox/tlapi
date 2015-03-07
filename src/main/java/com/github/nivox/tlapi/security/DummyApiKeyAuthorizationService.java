package com.github.nivox.tlapi.security;

public class DummyApiKeyAuthorizationService implements ApiKeyAuthorizationService {

    private String authorizedKey = null;

    public DummyApiKeyAuthorizationService(String authorizedKey) {
        this.authorizedKey = authorizedKey;
    }

    @Override
    public boolean authorize(String apiKey) {
        return apiKey.equals(authorizedKey);
    }
}
