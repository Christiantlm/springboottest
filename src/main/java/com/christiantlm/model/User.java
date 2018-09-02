package com.christiantlm.model;

/**
 * Created by dot.zhou on 2018/9/2.
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class User {

    private String userid;

    private String username;

    public User() {
    }

    public User(String userid, String username) {
        this.userid = userid;
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
