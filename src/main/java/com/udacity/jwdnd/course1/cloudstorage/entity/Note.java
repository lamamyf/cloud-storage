package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.Data;

@Data
public class Note {
    private int id;
    private String title;
    private String description;
    private int userId;
}
