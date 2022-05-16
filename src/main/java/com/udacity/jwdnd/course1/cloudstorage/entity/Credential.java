package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Credential {
    private int id;
    private String url;
    private String username;
    private String key;
    private String password;
    private int userId;

    public Credential(String url, String username, String key, String password, int userId){
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }
}
