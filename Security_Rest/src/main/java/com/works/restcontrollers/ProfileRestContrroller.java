package com.works.restcontrollers;

import com.works.profile.IConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileRestContrroller {

    final IConfig iConfig;
    public ProfileRestContrroller(IConfig iConfig) {
        this.iConfig = iConfig;
    }

    @GetMapping("/profile")
    public ResponseEntity profile() {
        return new ResponseEntity(iConfig.config(), HttpStatus.OK);
    }

}
