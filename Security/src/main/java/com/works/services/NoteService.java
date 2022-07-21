package com.works.services;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class NoteService {

    final NoteRepository nRepo;
    public NoteService(NoteRepository nRepo) {
        this.nRepo = nRepo;
    }

    public ResponseEntity save(Note note) {
        Map<String, Object> hm = new LinkedHashMap<>();
        note.setDate( new Date());
        hm.put("status", true);
        hm.put("result", nRepo.save(note));
        return new ResponseEntity(hm, HttpStatus.OK);
    }


}
