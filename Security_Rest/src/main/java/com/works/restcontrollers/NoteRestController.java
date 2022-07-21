package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.services.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/singleNote")
    public ResponseEntity singleNote( @RequestBody Note note) {
        return noteService.singleNote( note.getId() , note.getExtrakey() );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map exceptionHandler( MethodArgumentNotValidException ex ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        List<ObjectError> objectErrors = ex.getAllErrors();
        List<String> list = new ArrayList<>();
        for ( ObjectError item : objectErrors ) {
            String message = item.getDefaultMessage();
            list.add( message );
        }
        hm.put("errors", list);
        return hm;
    }

}
