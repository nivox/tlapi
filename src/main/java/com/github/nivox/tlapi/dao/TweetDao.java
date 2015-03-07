package com.github.nivox.tlapi.dao;

import com.github.nivox.tlapi.dto.Tweet;
import com.github.nivox.tlapi.dto.User;

import java.util.List;

public interface TweetDao {

    public User getUser(String username) throws TweetDaoException;

    public List<String> getUserFollowed(String username) throws TweetDaoException;

    public List<String> getUserFollowers(String username) throws TweetDaoException;

    public List<Tweet> getUserSelfTweets(String username, String filter) throws TweetDaoException;

    public List<Tweet> getUserFollowedTweets(String username, String filter) throws TweetDaoException;

    public List<Tweet> getUserTweets(String username, String filter) throws TweetDaoException;

    public void addFollowEdge(String fromUser, String toUser) throws TweetDaoException;

    public void removeFollowEdge(String fromUser, String toUser) throws TweetDaoException;
}
