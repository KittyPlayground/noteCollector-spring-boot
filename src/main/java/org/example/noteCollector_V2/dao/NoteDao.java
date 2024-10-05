package org.example.noteCollector_V2.dao;


import org.example.noteCollector_V2.entity.impl.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao  extends JpaRepository<NoteEntity, String> {
}
