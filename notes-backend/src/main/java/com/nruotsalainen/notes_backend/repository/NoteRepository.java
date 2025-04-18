package com.nruotsalainen.notes_backend.repository;

import com.nruotsalainen.notes_backend.model.Note;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAll(Sort sort);
}
