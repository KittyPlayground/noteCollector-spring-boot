package org.example.noteCollector_V2.controller;


import org.example.noteCollector_V2.customStatusCodes.SelectedNoteErrorStatus;
import org.example.noteCollector_V2.dto.NoteStatus;
import org.example.noteCollector_V2.dto.impl.NoteDTO;
import org.example.noteCollector_V2.exception.DataPersistException;
import org.example.noteCollector_V2.service.NoteService;
import org.example.noteCollector_V2.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, //serialization
            produces = MediaType.APPLICATION_JSON_VALUE)//deserialization

    public ResponseEntity<Void> saveNotes(@RequestBody NoteDTO noteDTO) {
        try {
            noteService.saveNote(noteDTO);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteStatus getSelectedNotes(@PathVariable("noteId") String noteId) {

       if (!RegexProcess.noteIdMatcher(noteId)) {
           return new SelectedNoteErrorStatus(1, "Note Id cannot be null or empty");
       }
        return noteService.getSelectedNotes(noteId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes() {
        return noteService.getAllNotes();
    }
    @DeleteMapping(value = "/{noteId}")
    public ResponseEntity<Void> deletedNotes(@PathVariable("noteId") String noteId) {
        try {
            if (!RegexProcess.noteIdMatcher(noteId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            noteService.deleteNote(noteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/{noteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateNote(@PathVariable("noteId") String noteId,
                           @RequestBody NoteDTO noteDTO) {
        try {
            if (!RegexProcess.noteIdMatcher(noteId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                noteService.update(noteId, noteDTO);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
