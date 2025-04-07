package com.nruotsalainen.notes_backend.repository;

import com.nruotsalainen.notes_backend.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> { }
