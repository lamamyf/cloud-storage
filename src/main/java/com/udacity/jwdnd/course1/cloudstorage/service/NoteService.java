package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.entity.mapper.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteMapper noteMapper;

    public void addNote(String title, String description, int userId) {
        noteMapper.save(new Note(title, description, userId));
    }

    public void updateNoteById(String title, String description, int id){
        noteMapper.updateNoteById(title, description, id);
    }

    public void deleteNoteById(int id) {
        noteMapper.deleteNoteById(id);
    }

    public List<Note> getNotesByUserId(int userId) {
        return noteMapper.getNotesByUserId(userId);
    }
}
