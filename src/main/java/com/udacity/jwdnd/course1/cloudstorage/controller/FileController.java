package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    @PostMapping("/file/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        fileService.uploadFile(file, userService.getCurrentUserId());
        redirectAttributes.addFlashAttribute("success", "File uploaded successfully.");
        return "redirect:/result";
    }

    @DeleteMapping("/file/{name}")
    public String deleteFile(@PathVariable String name, RedirectAttributes redirectAttributes){
        fileService.deleteFileByNameAndUserId(name, userService.getCurrentUserId());
        redirectAttributes.addFlashAttribute("success", "File deleted successfully.");
        return "redirect:/result";
    }

    @GetMapping(value = "/file/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] getFile(@PathVariable String name, HttpServletResponse response) throws IOException {
        return fileService.getFileByNameAndUserId(name, userService.getCurrentUserId()).getData();
    }
}
