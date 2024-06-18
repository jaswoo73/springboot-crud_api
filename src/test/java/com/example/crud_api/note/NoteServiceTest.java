package com.example.crud_api.note;

import com.example.crud_api.dto.NoteRequestDTO;
import com.example.crud_api.entity.Note;
import com.example.crud_api.repository.NoteRepository;
import com.example.crud_api.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {
    @Mock
    private NoteRepository noteRepository;
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        noteService = new NoteService(noteRepository);
    }

    @Test
    void canFetchAllNotes() {
        // when
        noteService.getNotes();
        // then
        verify(noteRepository).findAll();
    }

    @Test
    void canCreateNewNote() {
        // given
        Note note = new Note();
        note.setId(1);
        note.setTitle("Test Note");
        note.setContent("Test Content");
        when(noteRepository.save(any(Note.class))).thenReturn(note);
        // when
        NoteRequestDTO noteRequestDTO = new NoteRequestDTO(note.getTitle(), note.getContent());
        noteService.newNote(noteRequestDTO);
        // then
        ArgumentCaptor<Note> noteArgumentCaptor = ArgumentCaptor.forClass(Note.class);
        verify(noteRepository).save(noteArgumentCaptor.capture());
        Note capturedNote = noteArgumentCaptor.getValue();
        assertThat(capturedNote.getId()).isNotNull();
        assertThat(capturedNote.getTitle()).isEqualTo("Test Note");
        assertEquals("Test Content", capturedNote.getContent());
    }

    @Test
    @Disabled
    void updateNote() {
    }

    @Test
    @Disabled
    void deleteNote() {
    }

    @Test
    @Disabled
    void getNoteById() {
    }

    @Test
    @Disabled
    void getNoteByTitle() {
    }

    @Test
    @Disabled
    void findByKeyword() {
    }
}