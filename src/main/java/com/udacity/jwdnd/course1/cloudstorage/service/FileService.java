package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.BusinessException;
import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.FileBusinessError;
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

        boolean fileNameAlreadyUsed = fileMapper.existsByNameAndUserId(file.getOriginalFilename(), userId);

        if(fileNameAlreadyUsed){
            throw new BusinessException(FileBusinessError.FILE_NAME_ALREADY_USED.toServiceError(file.getOriginalFilename()));
        }

        try {
            fileMapper.save(new File(file.getOriginalFilename(), file.getContentType(), file.getSize()+"", userId, file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFileByNameAndUserId(String name, int userId) {
        fileMapper.deleteFileByNameAndUserId(name, userId);
    }

    public List<String> getFilesByUserId(int userId){
        return fileMapper.getFilesByUserId(userId);
    }

    public File getFileByNameAndUserId(String name, int userId){
        return fileMapper.getFileByNameAndUserId(name, userId);
    }
}
