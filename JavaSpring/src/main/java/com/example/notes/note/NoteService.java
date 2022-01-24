package com.example.notes.note;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableMapRepositories
public class NoteService {
  private final CrudRepository<Note, Long> repository;

  public NoteService(CrudRepository<Note, Long> repository) {
    this.repository = repository;
    this.repository.saveAll(defaultNotes());
  }

  private static List<Note> defaultNotes() {
    return List.of(
        new Note(1L, "First Note"),
        new Note(2L, "Second note"),
        new Note(3L, "A third note"));
  }

  public List<Note> findAll() {
    List<Note> list = new ArrayList<>();
    Iterable<Note> Notes = repository.findAll();
    Notes.forEach(list::add);
    return list;
  }

  public Optional<Note> find(Long id) {
    return repository.findById(id);
  }

  public Note create(Note Note) {
    // To ensure the Note ID remains unique,
    // use the current timestamp.
    Note copy = new Note(
        new Date().getTime(),
        Note.getName());
    return repository.save(copy);
  }

  public Optional<Note> update(Long id, Note newNote) {
    // Only update an Note if it can be found first.
    return repository.findById(id)
        .map(oldNote -> {
          Note updated = oldNote.updateWith(newNote);
          return repository.save(updated);
        });
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
