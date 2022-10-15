package com.optumhackbright.noteApp.controllers;

import com.optumhackbright.noteApp.dtos.NoteDto;
import com.optumhackbright.noteApp.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    //get all notes by user example:
    @GetMapping("/user/{userId}")
    public List<NoteDto> getNotesByUser(@PathVariable Long userId){
        return noteService.getAllNotesByUserId(userId);
    }
    //add a new note example:
    @PostMapping("/user/{userId}")
    public void addNote(@RequestBody NoteDto noteDto,@PathVariable Long userId){
        noteService.addNote(noteDto, userId);
    }
    //delete a note example:
    @DeleteMapping("/{noteId}")
    public void deleteNoteById(@PathVariable Long noteId){
        noteService.deleteNoteById(noteId);
    }
    //update an existing note example:
    @PutMapping
    public void updateNoteById(@RequestBody NoteDto noteDto){
        noteService.updateNoteById(noteDto);
    }
    //get a note by note's id example:
    @GetMapping("/{noteId}")
    public Optional<NoteDto> getNoteById(@PathVariable Long noteId){
        return noteService.getNoteById(noteId);
    }
}
