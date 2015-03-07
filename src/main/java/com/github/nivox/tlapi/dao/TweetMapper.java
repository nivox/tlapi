package com.github.nivox.tlapi.dao;


import com.github.nivox.tlapi.dto.Tweet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TweetMapper implements RowMapper<Tweet> {
    @Override
    public Tweet mapRow(ResultSet rs, int i) throws SQLException {
        Tweet tweet = new Tweet();

        tweet.setUserId(rs.getString("user"));
        tweet.setTweetId(rs.getLong("tweet_id"));
        tweet.setTimestamp(new Date(rs.getLong("timestamp") * 1000));
        tweet.setBody(rs.getString("tweet"));

        return tweet;
    }
}
