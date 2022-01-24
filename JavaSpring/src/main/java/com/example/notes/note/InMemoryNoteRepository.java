package com.example.notes.note;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryNoteRepository extends CrudRepository<Note, Long> {
}