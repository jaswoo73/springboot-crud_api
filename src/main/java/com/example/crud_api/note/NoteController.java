package com.example.crud_api.note;

import com.example.crud_api.dto.NoteDTO;
import com.example.crud_api.dto.NoteRequestDTO;
import com.example.crud_api.dto.ResponseDTO;
import com.example.crud_api.exception.NoteNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<NoteDTO>>>getAllNotes() {
        List<NoteDTO> notes = noteService.getNotes();
        ResponseDTO<List<NoteDTO>> response = new ResponseDTO<>(notes);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/new")
    public ResponseEntity<ResponseDTO<NoteDTO>> createNote(@Valid @RequestBody NoteRequestDTO noteRequestDTO) {
            NoteDTO createdNote = this.noteService.newNote(noteRequestDTO);
            ResponseDTO<NoteDTO> response = new ResponseDTO<>(createdNote);
            return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<NoteDTO>> updateNoteById(@PathVariable Integer id,
                                                               @Valid @RequestBody NoteRequestDTO noteRequestDTO,
                                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            ResponseDTO<NoteDTO> response = new ResponseDTO<>(422, errorMessage);
            return ResponseEntity.unprocessableEntity().body(response);
        }

        NoteDTO updatedNote;
        try {
            updatedNote = noteService.updateNote(id, noteRequestDTO);
        } catch (NoteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(404, ex.getMessage()));
        }

        ResponseDTO<NoteDTO> response = new ResponseDTO<>(updatedNote);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<NoteDTO>> deleteNoteById(@PathVariable Integer id) {
        try {
            noteService.deleteNote(id);
            return ResponseEntity.noContent().build();
        }  catch (NoteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(404, ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<NoteDTO>> getNoteById(@PathVariable Integer id) {
        try {
            NoteDTO note = noteService.getNoteById(id);
            ResponseDTO<NoteDTO> response = new ResponseDTO<>(note);
            return ResponseEntity.ok(response);
        } catch (NoteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(404, ex.getMessage()));
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<ResponseDTO<List<NoteDTO>>> getNoteByTitle(@PathVariable String title) {
        try {
            List<NoteDTO> notes = noteService.getNoteByTitle(title);
            ResponseDTO<List<NoteDTO>> response = new ResponseDTO<>(notes);
            return ResponseEntity.ok(response);
        } catch (NoteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(404, ex.getMessage()));
        }
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<ResponseDTO<List<NoteDTO>>> getNoteByKeyword(@PathVariable String keyword) {
        try {
            List<NoteDTO> notes = noteService.findByKeyword(keyword);
            ResponseDTO<List<NoteDTO>> response = new ResponseDTO<>(notes);
            return ResponseEntity.ok(response);
        } catch (NoteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(404, ex.getMessage()));
        }
    }
}
