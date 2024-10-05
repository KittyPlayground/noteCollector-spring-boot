package org.example.noteCollector_V2.service;



import org.example.noteCollector_V2.dto.NoteStatus;
import org.example.noteCollector_V2.dto.impl.NoteDTO;

import java.util.List;

public interface NoteService {

    void saveNote(NoteDTO noteDTO);
    List <NoteDTO> getAllNotes();
    NoteStatus getSelectedNotes (String noteId);
    void deleteNote(String noteId);
    void update (String noteId, NoteDTO noteDTO);

}
