package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;

    @GetMapping("/home")
    public String getHomePage(Note note, Model model){
        model.addAttribute("note", note);
        model.addAttribute("files", fileService.getFilesByUserId(1));
        model.addAttribute("notes", noteService.getNotesByUserId(1));
        return "home";
    }
}
