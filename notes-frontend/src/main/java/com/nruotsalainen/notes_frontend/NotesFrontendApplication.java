package com.nruotsalainen.notes_frontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NotesFrontendApplication {
	@Value("${server.port}")
	private String serverPort;

	@Value("${backend.url}")
	private String backendUrl;

	public static void main(String[] args) {
		SpringApplication.run(NotesFrontendApplication.class, args);
	}

	@PostConstruct
	public void init() {
		System.out.println("Notes Frontend started. Port:" + serverPort + ", backend: " + backendUrl);
	}
}
