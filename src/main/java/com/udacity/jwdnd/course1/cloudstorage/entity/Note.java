package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class Note {
    private String id;
    private String title;
    private String description;
    private int userId;

    public Note(String title, String description, int userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
    }
}
