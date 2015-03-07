package com.github.nivox.tlapi.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class UserInfo {
    private User user;
    private List<String> followers;
    private List<String> following;

    public UserInfo() {}

    public UserInfo(User user, List<String> followers, List<String> following) {
        this.user = user;
        this.followers = followers;
        this.following = following;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }
}
