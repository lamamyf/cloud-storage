package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.entity.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    public void uploadFile(MultipartFile file) {
        int userId = 1;
        try {
            fileMapper.save(new File(file.getOriginalFilename(), file.getContentType(), file.getSize()+"", userId, file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getFilesByUserId(int userId){
        return fileMapper.getFilesByUserId(userId);
    }

    public File getFileByNameAndUserId(String name, int userId){
        return fileMapper.getFileByNameAndUserId(name, userId);
    }
}
