package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.dto.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    @PostMapping
    public String addNote(NoteForm note, RedirectAttributes redirectAttributes) {
        noteService.addNote(note.title(), note.description(), userService.getCurrentUserId());
        redirectAttributes.addFlashAttribute("success", "Note added successfully.");
        return "redirect:/result";
    }

    @PostMapping("/{id}")
    public String updateNote(@PathVariable int id, NoteForm note, RedirectAttributes redirectAttributes) {
        noteService.updateNoteById(note.title(), note.description(), id);
        redirectAttributes.addFlashAttribute("success", "Note updated successfully.");
        return "redirect:/result";
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable int id, RedirectAttributes redirectAttributes){
        noteService.deleteNoteById(id);
        redirectAttributes.addFlashAttribute("success", "Note deleted successfully.");
        return "redirect:/result";
    }
}

