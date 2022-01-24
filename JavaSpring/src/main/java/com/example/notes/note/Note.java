package com.example.notes.note;

import org.springframework.data.annotation.Id;

public class Note {
  private final Long id;
  private final String name;

  public Note(
      Long id,
      String name) {
    this.id = id;
    this.name = name;
  }

  @Id
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Note updateWith(Note note) {
    return new Note(
        this.id,
        note.name);
  }

}
