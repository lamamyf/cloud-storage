package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FileService fileService;

    @PostMapping("/file/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        fileService.uploadFile(file);
        redirectAttributes.addFlashAttribute("success", "File uploaded successfully.");
        return "redirect:/result";
    }

    @DeleteMapping("/file/{name}")
    public String deleteFile(@PathVariable String name, RedirectAttributes redirectAttributes){
        fileService.deleteFileByNameAndUserId(name, 1);
        redirectAttributes.addFlashAttribute("success", "File deleted successfully.");
        return "redirect:/result";
    }

    @GetMapping("/file/{name}")
    public void getFile(@PathVariable String name, HttpServletResponse response) throws IOException {
        File file = fileService.getFileByNameAndUserId(name, 1);
        response.setContentType(file.getContentType());
        response.getOutputStream().write(file.getData());
        response.getOutputStream().close();
    }

    @GetMapping("/home")
    public String getHomePage(Model model){
        model.addAttribute("files", fileService.getFilesByUserId(1));
        return "home";
    }
}
