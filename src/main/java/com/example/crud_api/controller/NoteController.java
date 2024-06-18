package com.example.crud_api.controller;

import com.example.crud_api.dto.NoteDTO;
import com.example.crud_api.dto.NoteRequestDTO;
import com.example.crud_api.dto.ResponseDTO;
import com.example.crud_api.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<NoteDTO>>> getAllNotes() {
        List<NoteDTO> notes = noteService.getNotes();
        return ResponseEntity.ok(new ResponseDTO<>(null, null, notes));
    }

    @PostMapping("/new")
    public ResponseEntity<ResponseDTO<NoteDTO>> createNote(@Valid @RequestBody NoteRequestDTO noteRequestDTO) {
        NoteDTO createdNote = noteService.newNote(noteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(null, null, createdNote));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<NoteDTO>> updateNoteById(@PathVariable Integer id,
                                                               @Valid @RequestBody NoteRequestDTO noteRequestDTO) {
        NoteDTO updatedNote = noteService.updateNote(id, noteRequestDTO);
        return ResponseEntity.ok(new ResponseDTO<>(null, null, updatedNote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<NoteDTO>> deleteNoteById(@PathVariable Integer id) {
        NoteDTO deletedNote = noteService.deleteNote(id);
        return ResponseEntity.ok(new ResponseDTO<>(null, null, deletedNote));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<NoteDTO>> getNoteById(@PathVariable Integer id) {
        NoteDTO note = noteService.getNoteById(id);
        return ResponseEntity.ok(new ResponseDTO<>(null, null, note));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<ResponseDTO<List<NoteDTO>>> getNoteByTitle(@PathVariable String title) {
        List<NoteDTO> notes = noteService.getNoteByTitle(title);
        return ResponseEntity.ok(new ResponseDTO<>(null, null, notes));
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<ResponseDTO<List<NoteDTO>>> getNoteByKeyword(@PathVariable String keyword) {
        List<NoteDTO> notes = noteService.findByKeyword(keyword);
        return ResponseEntity.ok(new ResponseDTO<>(null, null, notes));
    }
}
