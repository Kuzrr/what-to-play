package com.example.what_to_play.services;


import com.example.what_to_play.tables.Games;
import com.example.what_to_play.tables.GamesRepo;
import com.example.what_to_play.tools.LoadUserGameInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GamesService {
    private final GamesRepo gamesRepo;

    @Autowired
    public GamesService(GamesRepo gamesRepo) {
        this.gamesRepo = gamesRepo;
    }


    public void loadGames( String steamId ) {
        LoadUserGameInfo loeader = new LoadUserGameInfo(steamId);
        loeader.loadGamesLibrary();
        ArrayList<Games> gamesToLoad = loeader.getUserGames();
        gamesRepo.saveAll(gamesToLoad);

    }
}
