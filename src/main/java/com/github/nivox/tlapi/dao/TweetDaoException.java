package com.github.nivox.tlapi.dao;

public class TweetDaoException extends Exception {

    public TweetDaoException() {}

    public TweetDaoException(String message) {
        super(message);
    }

    public TweetDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
