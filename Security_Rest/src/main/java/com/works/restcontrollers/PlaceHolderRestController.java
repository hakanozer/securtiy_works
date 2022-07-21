package com.works.restcontrollers;

import com.works.services.PlaceHolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceHolderRestController {


    final PlaceHolderService pService;
    public PlaceHolderRestController(PlaceHolderService pService) {
        this.pService = pService;
    }

    @GetMapping("/allUser")
    public ResponseEntity allUser() {
        return pService.allUser();
    }

}
