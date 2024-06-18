package com.example.crud_api.note;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {
    @Test
    void testValidNote() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("Test Content");
        assertEquals("Test Note", note.getTitle(), "Expected title to be 'Test Note'");
        assertEquals("Test Content", note.getContent(), "Expected content to be 'Test Content'");
        assertNull(note.getId(), "Expected id to be auto-generated");
    }
}
