package com.thevirtugroup.postitnote.repository;

import com.thevirtugroup.postitnote.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**

 The NoteRepository interface provides methods to perform CRUD operations on Note entities
 in the database using Spring Data JPA.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
