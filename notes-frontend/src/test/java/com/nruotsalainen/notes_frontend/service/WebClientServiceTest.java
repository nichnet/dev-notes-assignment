package com.nruotsalainen.notes_frontend.service;

import com.nruotsalainen.notes_frontend.model.Note;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebClientServiceTest {
    @Autowired
    private WebClient.Builder webClientBuilder;
    private WebClientService service;

    @Value("${backend.url}")
    private String BACKEND_URL;

    private String[] mockValues = new String[] {
        "This is my first note.",
        "This is a cool second note."
    };

    @BeforeAll
    public void setup() {
        service = new WebClientService(webClientBuilder, BACKEND_URL);
    }

    @Test
    public void testCreateNotes() {
        // Note 1
        System.out.println("Creating first note.");
        Note note1 = new Note();
        note1.setValue(mockValues[0]);

        // Create request.
        Note createdNote1 = service.create(note1).block();

        assertNotNull(createdNote1);
        assertEquals(createdNote1.getValue(), mockValues[0]);

        // Note 2
        System.out.println("Creating second note.");
        Note note2 = new Note();
        note2.setValue(mockValues[1]);

        // Create request.
        Note createdNote2 = service.create(note2).block();

        assertNotNull(createdNote2);
        assertEquals(createdNote2.getValue(), mockValues[1]);
        System.out.println("Notes successfully created.");
    }

    @Test
    public void testGetAllNotes() {
        // Test against the notes created in the prior test.
        System.out.println("Fetching notes.");

        List<Note> notes = service.getAll().block();

        assertNotNull(notes);
        // Assert the size is AT LEAST the size of the previously created test notes.
        assertTrue(notes.size() >= 2);
        System.out.println("Fetched " + notes.size() + " notes successfully.");
        assertTrue(notes.stream().anyMatch(note -> note.getValue().equals(mockValues[0])));
        assertTrue(notes.stream().anyMatch(note -> note.getValue().equals(mockValues[1])));
    }
}
