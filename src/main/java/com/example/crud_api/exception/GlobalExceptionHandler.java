package com.example.crud_api.exception;

import com.example.crud_api.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        ResponseDTO<Void> response = new ResponseDTO<>(400, errorMessage, null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDTO<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorMessage = "Parameter '" + ex.getName() + "' must be of type " + ex.getRequiredType().getSimpleName();
        ResponseDTO<Void> response = new ResponseDTO<>(400, errorMessage, null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<ResponseDTO<Void>> handleNoteNotFoundException(NoteNotFoundException ex) {
        ResponseDTO<Void> response = new ResponseDTO<>(404, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Void>> handleException(Exception ex) {
        ResponseDTO<Void> response = new ResponseDTO<>(500, "An unexpected error occurred.", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
