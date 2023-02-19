package com.thevirtugroup.postitnote.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * The Note class represents a note object that contains a title, description, and creation/update timestamps.
 */
@Entity
@Table(name = "notes")
public class Note {

    /**
     * The unique identifier of the note.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the note.
     */
    private String title;

    /**
     * The description of the note.
     */
    private String description;

    /**
     * The timestamp when the note was created.
     */
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @CreationTimestamp
    private Date createdAt;

    /**
     * The timestamp when the note was last updated.
     */
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @UpdateTimestamp
    private Date updatedAt;

    /**
     * Creates a new Note object with the given title and description.
     *
     * @param title The title of the note.
     * @param description The description of the note.
     */
    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Creates an empty Note object.
     */
    public Note() {

    }

    /**
     * Creates a new Note object with the given ID and description.
     *
     * @param id The unique identifier of the note.
     * @param summary The description of the note.
     */
    public Note(Long id, String summary) {
        this.id = id;
        this.description = summary;
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

    public void setDescription(String summary) {
        this.description = summary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
