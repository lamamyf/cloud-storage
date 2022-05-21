package com.udacity.jwdnd.course1.cloudstorage.entity.dto;

public record SignUpForm(String username,
                         String password,
                         String firstName,
                         String lastName) {
}
