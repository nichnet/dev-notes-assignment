package com.nruotsalainen.notes_backend.controller;

import com.nruotsalainen.notes_backend.controller.response.exception.BadRequestException;
import com.nruotsalainen.notes_backend.controller.response.exception.InvalidQueryParameterException;
import com.nruotsalainen.notes_backend.model.Note;
import com.nruotsalainen.notes_backend.service.NoteService;
import com.nruotsalainen.notes_backend.service.SomeThirdPartyService;
import com.nruotsalainen.notes_backend.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NoteController {
    private final NoteService service;
    private final SomeThirdPartyService someThirdPartyService; // Third-party service (mock).

    @Autowired
    public NoteController(NoteService service, SomeThirdPartyService someThirdPartyService) {
        this.service = service;
        this.someThirdPartyService = someThirdPartyService;
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody Note note) {
        Note createdNote = service.create(note);

        if(createdNote == null) {
            throw new BadRequestException("note could not be created.");
        }

        System.out.println("Created new note.");

        HttpStatus logResult = someThirdPartyService.pushToThirdPartyService(note);

        if (logResult.is2xxSuccessful()) {
            System.out.println("Logged new note successfully with third-party service.");
        } else {
            System.out.println("Failed to log new note with third-party service.");
        }

        return createdNote;
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes(@RequestParam(name = "tz", required = false, defaultValue =  "UTC") String timezoneId,
                                  @RequestParam(name = "order", required = false, defaultValue =  "desc") String order) {
        // Validate the timezone and order
        if (timezoneId != null && !DateUtils.isTimezoneValid(timezoneId)) {
            throw new InvalidQueryParameterException("timezone (tz) must be a valid timezone id. eg 'UTC', 'Asia/Tokyo'");
        }

        if (!order.equalsIgnoreCase("desc") && !order.equalsIgnoreCase("asc")) {
            throw new InvalidQueryParameterException("order must be value 'desc' or 'asc'");
        }

        List<Note> notes = service.getAll(timezoneId, order);
        System.out.println("Fetched: " + notes.size() + " notes.");
        return notes;
    }

    @GetMapping("/notes/{id}")
    public Optional<Note> getNoteById(@PathVariable long id) {
        return service.getById(id);
    }
}
