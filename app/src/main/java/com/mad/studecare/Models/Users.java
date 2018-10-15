package com.mad.studecare.Models;

public class Users {

    private static Users users;
    private String email;
    private String name;
    private String userId;
    private String accessToken;

    public static Users getInstance() {
        if (users == null) {
            users = new Users();
        }
        return users;
    }

    private Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
