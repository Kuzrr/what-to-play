package com.example.what_to_play.controllers;

import org.springframework.ui.Model;
import com.example.what_to_play.services.GamesService;
import com.example.what_to_play.tables.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TameplateController {

    GamesService gamesService;

    @Autowired
    public TameplateController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping
    public String homePage() {
        return "home";
    }
    @PostMapping("/load")
    public String loadUserGameLibrary(@RequestParam String steamId) {
        gamesService.loadGames(steamId);
        return "loadedPage";
    }

    @GetMapping("/selectionPage")
    public String selectionPage() {
        return "selectionPage";
    }

    @GetMapping("/allGames")
    public String allGames(Model model) {
        List<Games> games = gamesService.getAllGames();
        model.addAttribute("games", games);
        return "AllGames";
    }

    @GetMapping("/byPlaytime")
    public String byPlaytime(Model model, @RequestParam String time) {
        List<Games> games = gamesService.getAllGamesBetweenHoursPlayed(time);
        model.addAttribute("games", games);
        return "byPlaytime";
    }

}
