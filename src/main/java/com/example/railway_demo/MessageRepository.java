package com.example.railway_demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // JpaRepository gives us methods like findAll(), findById(), save(), etc.
}
