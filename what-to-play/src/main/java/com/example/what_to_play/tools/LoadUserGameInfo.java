package com.example.what_to_play.tools;

import com.example.what_to_play.tables.Games;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class LoadUserGameInfo {

    private String API_KEY;
    private String userId;
    private HttpClient client;

    private ArrayList<Games> userGames;


    public LoadUserGameInfo(String userId) {
        this.API_KEY = "6B8FFC968253508CCCE2E5C66068A448";
        this.userId = userId;
        this.client = HttpClient.newHttpClient();
        userGames = new ArrayList<>();
    }


    public void showGames() {
        for (Games game : userGames) {
            System.out.println(game);
        }
    }

    public void loadGamesLibrary() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=" + API_KEY + "&steamid=" + userId + "&format=json&include_appinfo=true"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.body());
            JsonNode gamesNode = root.path("response").path("games");
            int i = 0;
            for (JsonNode game : gamesNode) {
                Long appID = game.get("appid").asLong();
                String name = game.get("name").asText();
                int hoursPlayed = game.get("playtime_forever").asInt() / 60;
                String tag = getGameTagsApi2(appID);
                if (tag.equals("")) {
                    tag = getGameTagsApi1(appID);
                }
                userGames.add(new Games(appID, name, hoursPlayed, tag));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getGameTagsApi1(Long gameID) {
        String tags = "";
        String gameIdString = Long.toString(gameID);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://store.steampowered.com/api/appdetails?appids=" + gameIdString))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.body());
            JsonNode genreNode = root.path(gameIdString).path("data").path("genres");
            int i = 0;
            for (JsonNode genre : genreNode) {
                if (i == 0) {
                    tags = tags + genre.get("description").textValue();
                } else {
                    tags = tags + ", " + genre.get("description").textValue();
                }
                i++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }

    private String getGameTagsApi2(Long gameID) {
        String tags = "";
        String gameIdString = Long.toString(gameID);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://steamspy.com/api.php?request=appdetails&appid=" + gameIdString))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.body());
            tags = root.get("genre").asText();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }

    public ArrayList<Games> getUserGames() {
        return this.userGames;
    }
}
