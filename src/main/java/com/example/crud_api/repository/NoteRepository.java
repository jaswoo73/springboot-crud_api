package com.example.crud_api.repository;

import com.example.crud_api.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends BaseRepository<Note, Integer> {
}
