package com.example.what_to_play.services;


import com.example.what_to_play.tables.Games;
import com.example.what_to_play.tables.GamesRepo;
import com.example.what_to_play.tools.LoadUserGameInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GamesService {
    private final GamesRepo gamesRepo;

    //descending by hours_played
    Sort sort;

    @Autowired
    public GamesService(GamesRepo gamesRepo) {
        this.gamesRepo = gamesRepo;
        this.sort = Sort.by(Sort.Direction.DESC, "hoursPlayed");
    }

    public List<Games> getAllGames() {
        return gamesRepo.findAll(Sort.by(Sort.Direction.DESC, "hoursPlayed"));
    }

    public List<Games> getAllGamesBetweenHoursPlayed(String time) {
        String splitTime = Arrays.toString(time.split(" "));
        int min = Integer.parseInt(time.split(" ")[0]);
        int max = Integer.parseInt(time.split(" ")[1]);
        return gamesRepo.findByHoursPlayedBetween(min, max, sort);
    }

    public List<Games> getGamesByTags(String tag) {
        return gamesRepo.findByTagsContainingIgnoreCase(tag, sort);
    }


    public void loadGames( String steamId ) {
        LoadUserGameInfo loeader = new LoadUserGameInfo(steamId);
        loeader.loadGamesLibrary();
        ArrayList<Games> gamesToLoad = loeader.getUserGames();
        gamesRepo.saveAll(gamesToLoad);

    }
}
