package com.github.nivox.tlapi.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Tweet {

    private long tweetId;
    private String userId;
    private Date timestamp;
    private String body;

    public Tweet() {}

    public Tweet(long tweetId, String userId, Date timestamp, String body) {
        this.tweetId = tweetId;
        this.userId = userId;
        this.timestamp = timestamp;
        this.body = body;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public String getUserId() {
        return userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getBody() {
        return body;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
