package com.github.nivox.tlapi.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class TweetCollection {
    private List<Tweet> tweets;

    public TweetCollection() {}

    public TweetCollection(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
