package com.thevirtugroup.postitnote.exception;

/**

 This exception is thrown when a requested note cannot be found.
 */
public class NoteNotFoundException extends Exception {

    /**

     Constructs a new NoteNotFoundException with the specified detail message and cause.
     @param message the detail message (which is saved for later retrieval by the getMessage() method).
     @param e the cause (which is saved for later retrieval by the getCause() method).
     */
    public NoteNotFoundException(String message, Exception e) {
        super(message, e);
    }
}