package com.nruotsalainen.notes_backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class NotesBackendApplication {
	@Value("${server.port}")
	private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(NotesBackendApplication.class, args);
	}

	@PostConstruct
	public void init() {
		System.out.println("Notes Backend started. Port:" + serverPort);
	}
}
