package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class NoteRestController {

    final NoteService noteService;
    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/noteSave")
    public ResponseEntity noteSave(@Valid @RequestBody Note note) {
        return noteService.save(note);
    }


}
