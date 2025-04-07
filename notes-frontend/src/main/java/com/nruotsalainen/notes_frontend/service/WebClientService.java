package com.nruotsalainen.notes_frontend.service;

import com.nruotsalainen.notes_frontend.model.Note;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class WebClientService {
    // Using WebClient so its non-blocking.
    private final WebClient webClient;

    public WebClientService(WebClient.Builder webClientBuilder, @Value("${backend.url}") String backendUrl) {
        this.webClient = webClientBuilder.baseUrl(backendUrl).build();
    }

    public Mono<List<Note>> getAll() {
        Mono<List<Note>> response = webClient.get()
            .uri("notes?order=asc")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<Note>>() { });

        return response;
    }

    public Mono<Note> create(Note note) {
        Mono<Note> response = webClient.post()
            .uri("notes")
            .bodyValue(note)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<Note>() { });

        return response;
    }
}
