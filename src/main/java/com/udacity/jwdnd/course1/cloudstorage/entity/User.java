package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;
}
