package com.works.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrorPageController {

    @GetMapping("/error")
    public String getErrors() {
        return "error";
    }

    @PostMapping("/error")
    public String postErrors() {
        return "error";
    }

}
