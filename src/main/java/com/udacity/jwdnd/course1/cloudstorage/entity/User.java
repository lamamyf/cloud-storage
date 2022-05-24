package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class User {
    private int id;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;

    public User(String username, String salt, String password, String firstName, String lastName) {
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
