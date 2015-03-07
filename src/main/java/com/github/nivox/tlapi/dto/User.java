package com.github.nivox.tlapi.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    private String user;
    private String name;
    private String description;
    private String location;

    public User() {}

    public User(String user, String name, String description, String location) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
