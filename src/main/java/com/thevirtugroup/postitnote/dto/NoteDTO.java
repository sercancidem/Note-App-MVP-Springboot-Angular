package com.thevirtugroup.postitnote.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import java.util.Date;


/**
 A Data Transfer Object (DTO) representing a note.
 */
public class NoteDTO {

    @Id
    private Long id;

    private String title;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date timedate;

    /**
     Default constructor for the NoteDTO class.
     */
    public NoteDTO() {
    }

    /**
     Constructor for the NoteDTO class with all fields.
     @param id the ID of the note
     @param title the title of the note
     @param description the description of the note
     @param timedate the date and time of the note
     */
    public NoteDTO(Long id, String title, String description, Date timedate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timedate = timedate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimedate() {
        return timedate;
    }

    public void setTimedate(Date timedate) {
        this.timedate = timedate;
    }
}
