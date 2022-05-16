package com.udacity.jwdnd.course1.cloudstorage.entity.dto;

public record SimpleCredential(String url,
                               String username,
                               String encryptedPassword,
                               String password) {
}

