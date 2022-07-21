package com.works.services;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import com.works.utils.TinkEncDec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@PropertySource("application.properties")
public class NoteService {

    @Value("${key128bit.key}")
    private String key128bit;

    final NoteRepository nRepo;
    public NoteService(NoteRepository nRepo) {
        this.nRepo = nRepo;
    }

    public ResponseEntity save(Note note) {
        Map<String, Object> hm = new LinkedHashMap<>();
        note.setDate( new Date());
        hm.put("status", true);

        String key = note.getExtrakey();
        String title = TinkEncDec.encrypt(key128bit, note.getTitle(), key);
        note.setTitle( title );

        String detail = TinkEncDec.encrypt(key128bit, note.getDetail(), key);
        note.setDetail(detail);

        note.setExtrakey("secur");

        hm.put("result", nRepo.save(note));
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    // single note
    public ResponseEntity singleNote( long id, String extrakey ) {
        Map<String, Object> hm = new LinkedHashMap<>();

        Optional<Note> optionalNote = nRepo.findById( id );
        if (optionalNote.isPresent() ) {
            Note note = optionalNote.get();

            String title = TinkEncDec.decrypt(key128bit, note.getTitle(), extrakey );
            note.setTitle( title );

            String detail = TinkEncDec.decrypt(key128bit, note.getDetail(), extrakey);
            note.setDetail( detail );

            hm.put("status", true);
            hm.put("result", note);

        }else {
            hm.put("status", false);
            hm.put("result", id);
        }

        return new ResponseEntity(hm, HttpStatus.OK);
    }


}
