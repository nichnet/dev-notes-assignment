package com.nruotsalainen.notes_frontend.controller;

import com.nruotsalainen.notes_frontend.service.WebClientService;
import com.nruotsalainen.notes_frontend.model.Note;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
public class WebClientController {
    private final WebClientService service;

    public WebClientController(WebClientService service) {
        this.service = service;
    }

    @GetMapping("/notes")
    public Mono<List<Note>> getAll() {
        return service.getAll();
    }

    @PostMapping("/notes")
    public Mono<Note> create(@RequestBody Note note) {
        return service.create(note);
    }
}
