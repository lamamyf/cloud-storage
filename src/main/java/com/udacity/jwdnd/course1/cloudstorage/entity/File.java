package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.Data;

@Data
public class File {
    private int id;
    private String name;
    private String contentType;
    private String size;
    private int userId;
    private byte[] data;
}
