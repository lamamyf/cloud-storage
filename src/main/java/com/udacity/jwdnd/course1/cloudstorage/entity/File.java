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

    public File(String name, String contentType, String size, int userId, byte[] data){
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.userId = userId;
        this.data = data;
    }
}
