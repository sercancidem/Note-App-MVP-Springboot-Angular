package com.thevirtugroup.postitnote.service;

import com.thevirtugroup.postitnote.dto.NoteDTO;
import com.thevirtugroup.postitnote.exception.NoteNotFoundException;
import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing notes.
 */
@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    /**
     * Finds all notes and returns them as a list of NoteDTO objects.
     *
     * @return a list of NoteDTO objects representing all the notes in the repository.
     * @throws NoteNotFoundException if an error occurs while retrieving the notes.
     */
    public List<NoteDTO> findAllNotes() throws NoteNotFoundException {
        try {
            return noteRepository.findAll().stream().map(note -> new NoteDTO(note.getId(), note.getTitle(), note.getDescription(), note.getUpdatedAt())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new NoteNotFoundException("Error in finding All Notes", e);
        }
    }

    /**
     * Saves a new note in the repository and returns its representation as a NoteDTO object.
     *
     * @param noteDTO the NoteDTO object representing the note to be saved.
     * @return the NoteDTO object representing the saved note.
     * @throws NoteNotFoundException if an error occurs while saving the note.
     */
    public NoteDTO saveNote(NoteDTO noteDTO) throws NoteNotFoundException {
        try {
            Note note = noteRepository.save(new Note(noteDTO.getTitle(), noteDTO.getDescription()));
            return new NoteDTO(note.getId(), note.getTitle(), note.getDescription(), note.getUpdatedAt());
        } catch (Exception e) {
            throw new NoteNotFoundException("Error in saving note", e);
        }
    }

    /**
     * Updates an existing note in the repository and returns its representation as a NoteDTO object.
     *
     * @param noteDTO the NoteDTO object representing the note to be updated.
     * @return the NoteDTO object representing the updated note.
     * @throws NoteNotFoundException if an error occurs while updating the note.
     */
    public NoteDTO updateNote(NoteDTO noteDTO) throws NoteNotFoundException {
        try {
            Note note = noteRepository.findOne(noteDTO.getId());
            note.setDescription(noteDTO.getDescription());
            note.setTitle(noteDTO.getTitle());
            note = noteRepository.save(note);
            return new NoteDTO(note.getId(), note.getTitle(), note.getDescription(), note.getUpdatedAt());
        } catch (Exception e) {
            throw new NoteNotFoundException("Error in saving note", e);
        }
    }

    /**
     * Finds a note by its ID and returns its representation as a NoteDTO object.
     *
     * @param id the ID of the note to be retrieved.
     * @return the NoteDTO object representing the retrieved note.
     * @throws NoteNotFoundException if the note with the given ID is not found.
     */
    public NoteDTO findNoteById(Long id) throws NoteNotFoundException {
        try {
            Note note = noteRepository.findOne(id);
            return new NoteDTO(note.getId(), note.getDescription(), note.getTitle(), note.getUpdatedAt());
        } catch (Exception e) {
            throw new NoteNotFoundException("Error finding note by id", e);
        }
    }

    /**
     * Deletes a note by its ID.
     *
     * @param id the ID of the note to be deleted.
     * @throws NoteNotFoundException if an error occurs while deleting the note or the note with the given ID is not found.
     */
    public void deleteNoteById(Long id) throws NoteNotFoundException {
        try {
            noteRepository.delete(id);
        } catch (Exception e) {
            throw new NoteNotFoundException("Error deleting note by id", e);
        }
    }
}
