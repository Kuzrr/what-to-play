//package com.example.what_to_play.tools;
//
//import com.example.what_to_play.services.GamesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Runner implements CommandLineRunner {
//
//    public final GamesService gamesService;
//
//    @Autowired
//    public Runner(GamesService gamesService) {
//        this.gamesService = gamesService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        gamesService.loadGames("76561198158132974");
//    }
//}
