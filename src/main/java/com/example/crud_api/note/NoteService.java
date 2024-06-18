package com.example.crud_api.note;

import com.example.crud_api.dto.NoteDTO;
import com.example.crud_api.dto.NoteRequestDTO;
import com.example.crud_api.exception.NoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteDTO newNote(NoteRequestDTO noteRequestDTO) {
        Note note = new Note();
        note.setTitle(noteRequestDTO.getTitle());
        note.setContent(noteRequestDTO.getContent());
        Note savedNote = this.noteRepository.save(note);
        return new NoteDTO(savedNote.getId(), savedNote.getTitle(), savedNote.getContent());
    }

    public List<NoteDTO> getNotes() {
        return this.noteRepository.findAll().stream().map(note -> new NoteDTO(note.getId(), note.getTitle(), note.getContent())).collect(Collectors.toList());
    }

    public NoteDTO updateNote(Integer id, NoteRequestDTO updatedNoteRequestDTO) {
        Optional<Note> noteOptional = this.noteRepository.findById(id);

        if (noteOptional.isEmpty()) {
            throw new NoteNotFoundException("No note found with id: " + id);
        }

        Note existingNote = noteOptional.get();
        existingNote.setTitle(updatedNoteRequestDTO.getTitle());
        existingNote.setContent(updatedNoteRequestDTO.getContent());
        Note savedNote = this.noteRepository.save(existingNote);
        return new NoteDTO(savedNote.getId(), savedNote.getTitle(), savedNote.getContent());
    }

    public void deleteNote(Integer id) {
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException("No note found with id: " + id);
        }
        this.noteRepository.deleteById(id);
    }

    public NoteDTO getNoteById(Integer id) {
        Optional<Note> noteOptional = this.noteRepository.findById(id);

        if (noteOptional.isEmpty()) {
            throw new NoteNotFoundException("No note found with id: " + id);
        }

        Note note = noteOptional.get();
        return new NoteDTO(note.getId(), note.getTitle(), note.getContent());
    }

    public List<NoteDTO> getNoteByTitle(String title) {
        List<Note> notes = this.noteRepository.findByTitle(title);

        if (notes.isEmpty()) {
            throw new NoteNotFoundException("No note found with title: " + title);
        }

        return notes.stream().map(note -> new NoteDTO(note.getId(), note.getTitle(), note.getContent())).collect(Collectors.toList());
    }

    public List<NoteDTO> findByKeyword(String keyword) {
        List<Note> notes = this.noteRepository.findByKeyword(keyword);

        if (notes.isEmpty()) {
            throw new NoteNotFoundException("No note found with keyword: " + keyword);
        }

        return notes.stream().map(note -> new NoteDTO(note.getId(), note.getTitle(), note.getContent())).collect(Collectors.toList());
    }
}
