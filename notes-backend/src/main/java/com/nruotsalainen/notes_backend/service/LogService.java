package com.nruotsalainen.notes_backend.service;

import com.nruotsalainen.notes_backend.model.Log;
import com.nruotsalainen.notes_backend.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This LogService is to mock a third-party service as specified in the req's.
 */
@Service
public class LogService {

    @Autowired
    private LogRepository repository;

    public Log create(Log log) {
       return repository.save(log);
    }
}
