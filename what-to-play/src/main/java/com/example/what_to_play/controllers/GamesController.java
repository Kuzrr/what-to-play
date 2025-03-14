package com.example.what_to_play.controllers;

import com.example.what_to_play.services.GamesService;
import com.example.what_to_play.tables.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GamesController {

    private final GamesService gamesService;

    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @PostMapping("/load")
    public String loadUserGameLibrary(@RequestParam String steamId) {
        gamesService.loadGames(steamId);
        return "User game library loaded";
    }

    @GetMapping("/show/allGames")
    public List<Games> showAllGames(){
        return gamesService.getAllGames();
    }

    @GetMapping("/show/byPlaytime")
    public List<Games> showGamesByPlayTime(@RequestParam(defaultValue = "0") int minTime, @RequestParam(defaultValue = "100000") int maxTime){
        return gamesService.getAllGamesBetweenHoursPlayed(minTime,maxTime);
    }

    @GetMapping("/show/byTags")
    public List<Games> showGamesByTags(@RequestParam String tag){
        return gamesService.getGamesByTags(tag);
    }

}
