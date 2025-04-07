package com.nruotsalainen.notes_backend.service;

import com.nruotsalainen.notes_backend.model.Note;
import com.nruotsalainen.notes_backend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository repository;

    public List<Note> getAll(String timezoneId, String order) {
        Sort.Direction direction = Sort.Direction.DESC;

        if("asc".equalsIgnoreCase(order)) {
            direction = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(direction, "dateCreated");
        List<Note> notes = repository.findAll(sort);

        for(Note note : notes) {
            note.setTimezone(timezoneId);
        }

        return notes;
    }

    public Optional<Note> getById(long id) {
        return repository.findById(id);
    }

    public Note create(Note note) {
       return repository.save(note);
    }
}
