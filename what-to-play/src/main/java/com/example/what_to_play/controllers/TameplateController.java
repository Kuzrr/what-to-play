package com.example.what_to_play.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TameplateController {

    @GetMapping
    public String homePage() {
        return "home";
    }

}
