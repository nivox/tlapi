package com.github.nivox.tlapi.api;

import com.github.nivox.tlapi.dao.TweetDao;
import com.github.nivox.tlapi.dto.Tweet;
import com.github.nivox.tlapi.dto.TweetCollection;
import com.github.nivox.tlapi.dto.User;
import com.github.nivox.tlapi.dto.UserInfo;
import com.github.nivox.tlapi.security.ApiKeyAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TweetController {

    @Autowired
    private ApiKeyAuthorizationService authService;

    @Autowired
    private TweetDao tweetDao;

    @RequestMapping(value="/tweets", produces={"application/json", "application/xml"})
    public ResponseEntity<TweetCollection> tweets(
        @RequestParam(value="apiKey", required=true) String apiKey,
        @RequestParam(value="user", required=true) String user,
        @RequestParam(value="filter", required=false) String filter
    ) {
        if (!authService.authorize(apiKey)) return new ResponseEntity<TweetCollection>(HttpStatus.UNAUTHORIZED);

        try {
            List<Tweet> tweets = tweetDao.getUserTweets(user, filter);
            return new ResponseEntity<TweetCollection>(new TweetCollection(tweets), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TweetCollection>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/info", produces={"application/json", "application/xml"})
    public ResponseEntity<UserInfo> tweets(
        @RequestParam(value="apiKey", required=true) String apiKey,
        @RequestParam(value="user", required=true) String user,
        @RequestParam(value="includeFollowers", required=false, defaultValue="false") Boolean includeFollowers,
        @RequestParam(value="includeFollowing", required=false, defaultValue="false") Boolean includeFollowing
    ) {
        if (!authService.authorize(apiKey)) return new ResponseEntity<UserInfo>(HttpStatus.UNAUTHORIZED);

        try {
            User userInstance = tweetDao.getUser(user);
            if (userInstance == null) return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);

            List<String> followers = null;
            List<String> following = null;

            if (includeFollowers) followers = tweetDao.getUserFollowers(user);
            if (includeFollowing) following = tweetDao.getUserFollowed(user);

            return new ResponseEntity<UserInfo>(new UserInfo(userInstance, followers, following), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value="/follow", produces={"application/json", "application/xml"})
    public ResponseEntity<Void> follow(
        @RequestParam(value="apiKey", required=true) String apiKey,
        @RequestParam(value="user", required=true) String user,
        @RequestParam(value="followUser", required=true) String followUser
    ) {
        if (!authService.authorize(apiKey)) return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);

        try {
            tweetDao.addFollowEdge(user, followUser);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/unfollow", produces={"application/json", "application/xml"})
    public ResponseEntity<Void> unfollow(
        @RequestParam(value="apiKey", required=true) String apiKey,
        @RequestParam(value="user", required=true) String user,
        @RequestParam(value="followedUser", required=true) String followedUser
    ) {
        if (!authService.authorize(apiKey)) return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);

        try {
            tweetDao.removeFollowEdge(user, followedUser);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
