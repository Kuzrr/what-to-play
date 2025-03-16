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
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<String> getAllUniqueTags(){
        List<String> tagStrings = gamesRepo.findAllTags();
        return tagStrings.stream()
                .flatMap(tags -> Arrays.stream(tags.split(", ")))
                .map(String::trim)
                .filter(tag -> !tag.isEmpty())
                .collect(Collectors.toSet());

    }
    public List<Games> x(){
        List<Games> gamesList = gamesRepo.findByHoursAndTag(50,250,"Action", sort);
        return gamesList;
    }


    public List<Games> getAllGamesBetweenHoursPlayedAndContaingTags(String time, String tag) {
        int min = Integer.parseInt(time.split(" ")[0]);
        int max = Integer.parseInt(time.split(" ")[1]);
        return gamesRepo.findByHoursAndTag(min, max, tag, sort);
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
