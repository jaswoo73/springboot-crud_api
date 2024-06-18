package com.example.crud_api.model;

public class NoteServiceModel {
    private String title;
    private String content;

    public NoteServiceModel() {
    }

    public NoteServiceModel(String title, String content) {
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
