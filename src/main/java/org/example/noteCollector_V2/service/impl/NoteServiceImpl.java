package org.example.noteCollector_V2.service.impl;

import jakarta.transaction.Transactional;
import org.example.noteCollector_V2.customStatusCodes.SelectedNoteErrorStatus;
import org.example.noteCollector_V2.dao.NoteDao;
import org.example.noteCollector_V2.dto.NoteStatus;
import org.example.noteCollector_V2.dto.impl.NoteDTO;
import org.example.noteCollector_V2.entity.impl.NoteEntity;
import org.example.noteCollector_V2.exception.DataPersistException;
import org.example.noteCollector_V2.service.NoteService;
import org.example.noteCollector_V2.util.AppUtil;
import org.example.noteCollector_V2.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveNote(NoteDTO noteDTO) {

        noteDTO.setNoteId(AppUtil.generateNoteId());
        NoteEntity saved = noteDao.save(mapping.toNoteEntity(noteDTO));
        if (saved == null) {
            throw new DataPersistException("Note not saved");
        }
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        List<NoteEntity> all = noteDao.findAll();
        return mapping.asNoteDTOList(all);
    }

    @Override
    public NoteStatus getSelectedNotes(String noteId) {
        if(noteDao.existsById(noteId)) {
            NoteEntity selectedNote = noteDao.getReferenceById(noteId);
            return mapping.toNoteDTO(selectedNote);
        } else {
            return new SelectedNoteErrorStatus(2, "Note not found");
        }
    }

    @Override
    public void deleteNote(String noteId) {
        Optional<NoteEntity> existsById = noteDao.findById(noteId);
        if(!existsById.isPresent()) {
            throw new DataPersistException("Note with id " + noteId + " not found");
        } else {
            noteDao.deleteById(noteId);
        }
    }

    @Override
    public void update(String noteId, NoteDTO noteDTO) {
        Optional<NoteEntity> tmpNote = noteDao.findById(noteId);
        if(tmpNote.isPresent()) {
            tmpNote.get().setNoteTitle(noteDTO.getNoteTitle());
            tmpNote.get().setNoteDesc(noteDTO.getNoteDesc());
            tmpNote.get().setPriorityLevel(noteDTO.getPriorityLevel());
            tmpNote.get().setCreateDate(noteDTO.getCreateDate());



        }

    };
}

