package org.example.noteCollector_V2.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.noteCollector_V2.dto.NoteStatus;
import org.example.noteCollector_V2.dto.SuperDTO;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDTO implements NoteStatus, SuperDTO {

    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String createDate;
    private String priorityLevel;
    private String userId;
}
