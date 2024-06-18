package com.example.crud_api.service;

import com.example.crud_api.dto.NoteDTO;
import com.example.crud_api.dto.NoteRequestDTO;
import com.example.crud_api.entity.Note;
import com.example.crud_api.exception.NoteNotFoundException;
import com.example.crud_api.mapper.NoteMapper;
import com.example.crud_api.model.NoteServiceModel;
import com.example.crud_api.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Autowired
    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public NoteDTO newNote(NoteRequestDTO noteRequestDTO) {
        NoteServiceModel serviceModel = noteMapper.toServiceModel(noteRequestDTO);
        Note savedNote = noteRepository.save(noteMapper.toEntity(serviceModel));
        return noteMapper.toDTO(savedNote);
    }

    public List<NoteDTO> getNotes() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream()
                .map(noteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public NoteDTO updateNote(Integer id, NoteRequestDTO updatedNoteRequestDTO) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("No note found with id: " + id));

        note.setTitle(updatedNoteRequestDTO.getTitle());
        note.setContent(updatedNoteRequestDTO.getContent());

        Note savedNote = noteRepository.save(note);
        return noteMapper.toDTO(savedNote);
    }

    public NoteDTO deleteNote(Integer id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("No note found with id: " + id));

        noteRepository.deleteById(id);
        return noteMapper.toDTO(note);
    }

    public NoteDTO getNoteById(Integer id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("No note found with id: " + id));

        return noteMapper.toDTO(note);
    }

    public List<NoteDTO> getNoteByTitle(String title) {
        List<Note> notes = noteRepository.findByTitle(title);
        if (notes.isEmpty()) {
            throw new NoteNotFoundException("No note found with title: " + title);
        }
        return notes.stream()
                .map(noteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<NoteDTO> findByKeyword(String keyword) {
        List<Note> notes = noteRepository.findByKeyword(keyword);
        if (notes.isEmpty()) {
            throw new NoteNotFoundException("No note found with keyword: " + keyword);
        }
        return notes.stream()
                .map(noteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
