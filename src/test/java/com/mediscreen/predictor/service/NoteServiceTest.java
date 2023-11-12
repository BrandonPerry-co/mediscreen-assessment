package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NotesService notesServiceMock;

    @Test
    public void testNotesService() {
        int id = 1;
        List<Notes> testNotes = List.of(Notes.builder().patId(id).note("Patient: has red blood None None none Dizziness Dizziness smoker A1c").build());
        String expected = "[Notes(id=null, patId=1, note=Patient: has red blood None None none Dizziness Dizziness smoker A1c)]";
        when(notesServiceMock.getNotesByPatientId(id)).thenReturn(testNotes);
        String result = notesServiceMock.getNotesByPatientId(id).toString();
        assertThat(result).isEqualTo(expected);
    }
}
