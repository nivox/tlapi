package com.github.nivox.tlapi.dao;

import com.github.nivox.tlapi.dto.Tweet;
import com.github.nivox.tlapi.dto.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TweetDaoImpl implements TweetDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private UserMapper userMapper = new UserMapper();
    private TweetMapper tweetMapper = new TweetMapper();

    public void setDataSource(DataSource ds) {
        jdbcTemplate = new NamedParameterJdbcTemplate(ds);
    }

    @Override
    public User getUser(String username) throws TweetDaoException {
        Map params = new HashMap<String, Object>();
        params.put("username", username);

        try {
            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user=:username", params, new UserMapper());
        } catch(Exception e) {
            throw new TweetDaoException("Error while fetching user: " + username, e);
        }
    }

    @Override
    public List<String> getUserFollowed(String username) throws TweetDaoException {
        Map params = new HashMap<String, Object>();
        params.put("username", username);

        try {
            return jdbcTemplate.queryForList("SELECT follows FROM user_follow_edges WHERE user=:username", params, String.class);
        } catch(Exception e) {
            throw new TweetDaoException("Error while fetching users followed by user" + username, e);
        }
    }

    @Override
    public List<String> getUserFollowers(String username) throws TweetDaoException {
        Map params = new HashMap<String, Object>();
        params.put("username", username);

        try {
            return jdbcTemplate.queryForList("SELECT user FROM user_follow_edges WHERE follows=:username", params, String.class);
        } catch(Exception e) {
            throw new TweetDaoException("Error while fetching users following user" + username, e);
        }
    }

    @Override
    public List<Tweet> getUserSelfTweets(String username, String filter) throws TweetDaoException {
        Map params = new HashMap<String, Object>();
        params.put("username", username);

        try {

            String sql = "SELECT * FROM tweets WHERE user=:username";
            if (filter != null) {
                params.put("filter", "%" + filter + "%");
                sql += " AND tweet LIKE :filter";
            }
            return jdbcTemplate.query(sql, params, tweetMapper);
        } catch(Exception e) {
            throw new TweetDaoException("Error while fetching users following user" + username, e);
        }
    }

    @Override
    public List<Tweet> getUserFollowedTweets(String username, String filter) throws TweetDaoException {
        Map params = new HashMap<String, Object>();
        params.put("username", username);

        try {

            String sql = "SELECT * FROM tweets WHERE user IN (SELECT follows FROM user_follow_edges WHERE user=:username)";
            if (filter != null) {
                params.put("filter", "%" + filter + "%");
                sql += " AND tweet LIKE :filter";
            }
            return jdbcTemplate.query(sql, params, tweetMapper);
        } catch(Exception e) {
            throw new TweetDaoException("Error while fetching users following user" + username, e);
        }
    }

    @Override
    public List<Tweet> getUserTweets(String username, String filter) throws TweetDaoException {
        List<Tweet> tweets = getUserSelfTweets(username, filter);
        tweets.addAll(getUserFollowedTweets(username, filter));

        return tweets;
    }

    @Override
    public void addFollowEdge(String fromUser, String toUser) throws TweetDaoException {
        if (fromUser == null || toUser == null) throw new IllegalArgumentException("Both fromUser and toUser must be specified!");
        if (fromUser.equals(toUser)) throw new IllegalArgumentException("A user cannot follow himself!");

        Map params = new HashMap<String, Object>();
        params.put("from", fromUser);
        params.put("to", toUser);

        try {
            jdbcTemplate.update("INSERT INTO user_follow_edges (user, follows) VALUES (:from, :to)", params);
        } catch(DuplicateKeyException e) {
            // The user was already following the target user... Play it cool
        } catch (Exception e) {
            throw new TweetDaoException("Error adding follow edge from '" + fromUser + "' to '" + toUser + "'", e);
        }
    }

    @Override
    public void removeFollowEdge(String fromUser, String toUser) throws TweetDaoException {
        if (fromUser == null || toUser == null) throw new IllegalArgumentException("Both fromUser and toUser must be specified!");
        if (fromUser.equals(toUser)) throw new IllegalArgumentException("A user cannot follow himself!");

        Map params = new HashMap<String, Object>();
        params.put("from", fromUser);
        params.put("to", toUser);

        try {
            jdbcTemplate.update("DELETE FROM user_follow_edges WHERE user=:from AND follows=:to", params);
        } catch (Exception e) {
            throw new TweetDaoException("Error removing follow edge from '" + fromUser + "' to '" + toUser + "'", e);
        }
    }
}
