package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.dto.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.entity.dto.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final UserService userService;

    @GetMapping("/home")
    public String getHomePage(NoteForm note, CredentialForm credential, Model model){
        model.addAttribute("note", note);
        model.addAttribute("credential", credential);

        model.addAttribute("files", fileService.getFilesByUserId(userService.getCurrentUserId()));
        model.addAttribute("notes", noteService.getNotesByUserId(userService.getCurrentUserId()));
        model.addAttribute("credentials", credentialService.getCredentialsByUserId(userService.getCurrentUserId()));
        return "home";
    }
}
