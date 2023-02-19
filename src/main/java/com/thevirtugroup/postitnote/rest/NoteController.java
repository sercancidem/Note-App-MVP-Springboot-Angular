package com.thevirtugroup.postitnote.rest;

import com.thevirtugroup.postitnote.dto.NoteDTO;
import com.thevirtugroup.postitnote.exception.NoteNotFoundException;
import com.thevirtugroup.postitnote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 This class represents the REST API controller for managing notes.
 It exposes CRUD operations for creating, reading, updating and deleting notes.
 */
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    /**
     The NoteService instance used to interact with the persistence layer.
     */
    @Autowired
    private NoteService noteService;

    /**
     Retrieves all notes.
     @return A list of NoteDTO objects.
     @throws NoteNotFoundException If no notes are found in the persistence layer.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<NoteDTO> getAllNotes() throws NoteNotFoundException {
        return noteService.findAllNotes();
    }

    /**
     Creates a new note.
     @param note The NoteDTO object representing the note to be created.
     @return The NoteDTO object representing the created note.
     @throws NoteNotFoundException If the note could not be created.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public NoteDTO createNote(@RequestBody NoteDTO note) throws NoteNotFoundException {
        return noteService.saveNote(note);
    }

    /**
     Updates an existing note.
     @param note The NoteDTO object representing the updated note.
     @return The NoteDTO object representing the updated note.
     @throws NoteNotFoundException If the note could not be updated.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public NoteDTO updateNote(@RequestBody NoteDTO note) throws NoteNotFoundException {
        return noteService.updateNote(note);
    }

    /**
     Retrieves a note by its ID.
     @param noteId The ID of the note to retrieve.
     @return The NoteDTO object representing the retrieved note.
     @throws NoteNotFoundException If the note could not be found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NoteDTO getNoteById(@PathVariable(value = "id") Long noteId) throws NoteNotFoundException {
        return noteService.findNoteById(noteId);
    }

    /**
     Deletes a note by its ID.
     @param id The ID of the note to delete.
     @throws NoteNotFoundException If the note could not be found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNoteById(@PathVariable Long id) throws NoteNotFoundException {
        noteService.deleteNoteById(id);
    }
}