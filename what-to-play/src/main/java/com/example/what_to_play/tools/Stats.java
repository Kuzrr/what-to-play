package com.example.what_to_play.tools;

public class Stats {

    private int totalPlaytime;
    private String favoriteGame;
    private String favoriteCategory;
    private int gamesCount;


    public Stats(String favoriteGame, String favoriteCategory, int gamesCount, int totalPlaytime) {
        this.favoriteGame = favoriteGame;
        this.favoriteCategory = favoriteCategory;
        this.gamesCount = gamesCount;
        this.totalPlaytime = totalPlaytime;
    }

    public int getTotalPlaytime() {
        return totalPlaytime;
    }

    public int getGamesCount() {
        return gamesCount;
    }

    public String getFavoriteCategory() {
        return favoriteCategory;
    }

    public String getFavoriteGame() {
        return favoriteGame;
    }




}
