package org.example.noteCollector_V2.customStatusCodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.noteCollector_V2.dto.NoteStatus;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectedNoteErrorStatus implements NoteStatus {
    private int statusCode;
    private  String statusMessage;
}
