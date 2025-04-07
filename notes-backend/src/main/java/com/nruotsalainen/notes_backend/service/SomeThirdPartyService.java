package com.nruotsalainen.notes_backend.service;

import com.nruotsalainen.notes_backend.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SomeThirdPartyService {
    @Value("${thirdparty.url}")
    private String URL;
    private final RestTemplate restTemplate;

    @Autowired
    public SomeThirdPartyService(RestTemplate restTemplate) {
        // Using RestTemplate as this should be blocking.
        // Need to check response of log creation before returning the note data to the user.
        this.restTemplate = restTemplate;
    }

    /**
     * Log the creation of a note with the third-party service.
     */
    public HttpStatus pushToThirdPartyService(Note note) {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("value", note.toString());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody);
        ResponseEntity<Void> response = restTemplate.exchange(URL, HttpMethod.POST, request, Void.class);
        return response.getStatusCode();
    }
}
