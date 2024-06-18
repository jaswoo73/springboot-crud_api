package com.example.crud_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoteRequestDTO {
    @NotBlank(message = "Title is required")
    @Size(max = 30, message = "Title must be less than or equal to 30 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(max = 80, message = "Content must be less than or equal to 80 characters")
    private String content;

    public NoteRequestDTO() {
    }

    public NoteRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
