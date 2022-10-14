package com.optumhackbright.noteApp.services;

import com.optumhackbright.noteApp.dtos.NoteDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface NoteService {
    //adding a note
    @Transactional
    void addNote(NoteDto noteDto, Long userId);

    //delete a note
    @Transactional
    void deleteNoteById(Long noteId);

    //update a note
    @Transactional
    void updateNoteById(NoteDto noteDto);

    List<NoteDto> getAllNotesByUserId(Long userId);

    //method for getting a Note by the Note "id"
    Optional<NoteDto> getNoteById(Long noteId);
}
