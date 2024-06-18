package com.example.crud_api.note;

import com.example.crud_api.entity.Note;
import com.example.crud_api.repository.NoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @AfterEach
    void tearDown() {
        noteRepository.deleteAll();
    }

    @Test
    void findByTitle() {
        // given
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("Test Content");
        noteRepository.save(note);
        // when
        List<Note> notes = noteRepository.findByTitle("Test Note");
        // then
        assertEquals(1, notes.size());
        assertEquals("Test Note", notes.get(0).getTitle());
        assertEquals("Test Content", notes.get(0).getContent());
    }

    @Test
    void findByWrongTitle() {
        // given
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("Test Content");
        noteRepository.save(note);
        // when
        List<Note> notes = noteRepository.findByTitle("Jest Note");
        // then
        assertEquals(0, notes.size());
        assertTrue(notes.isEmpty());
    }

    @Test
    void findByKeyword() {
        // given
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("Test Content");
        noteRepository.save(note);
        // when
        List<Note> notes = noteRepository.findByKeyword("Te");
        // then
        assertEquals(1, notes.size());
        assertEquals("Test Note", notes.get(0).getTitle());
        assertEquals("Test Content", notes.get(0).getContent());
    }

    @Test
    void findByWrongKeyword() {
        // given
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("Test Content");
        noteRepository.save(note);
        // when
        List<Note> notes = noteRepository.findByKeyword("A");
        // then
        assertEquals(0, notes.size());
        assertTrue(notes.isEmpty());
    }
}