package com.example.notes.note;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/notes/note")
public class NoteController {
  private final NoteService service;

  public NoteController(NoteService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Note>> findAll() {
    List<Note> Notes = service.findAll();
    return ResponseEntity.ok().body(Notes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Note> find(@PathVariable("id") Long id) {
    Optional<Note> Note = service.find(id);
    return ResponseEntity.of(Note);
  }

  @PostMapping
  public ResponseEntity<Note> create(@RequestBody Note Note) {
    Note created = service.create(Note);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(created.getId())
        .toUri();
    return ResponseEntity.created(location).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Note> update(
      @PathVariable("id") Long id,
      @RequestBody Note updatedNote) {

    Optional<Note> updated = service.update(id, updatedNote);

    return updated
        .map(value -> ResponseEntity.ok().body(value))
        .orElseGet(() -> {
          Note created = service.create(updatedNote);
          URI location = ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(created.getId())
              .toUri();
          return ResponseEntity.created(location).body(created);
        });
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Note> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
