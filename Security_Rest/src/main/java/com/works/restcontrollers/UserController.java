package com.works.restcontrollers;

import com.works.entities.UserX;
import com.works.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UserController {

    final UserService uService;
    public UserController(UserService uService) {
        this.uService = uService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserX user) {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {
            UserX u = uService.register(user);
            hm.put("status", true);
            hm.put("result", u);
        }catch (Exception ex) {
            hm.put("status", false);
            hm.put("result", user);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }


}
