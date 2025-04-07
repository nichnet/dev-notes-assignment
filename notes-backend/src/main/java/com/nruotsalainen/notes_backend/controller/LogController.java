package com.nruotsalainen.notes_backend.controller;

import com.nruotsalainen.notes_backend.model.Log;
import com.nruotsalainen.notes_backend.service.LogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LogController {
    private final LogService service;

    public LogController(LogService logService) {
        this.service = logService;
    }

    @PostMapping("/logs")
    public Log createNote(@RequestBody Log log) {
        return service.create(log);
    }
}
