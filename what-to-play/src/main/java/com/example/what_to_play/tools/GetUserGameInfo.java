package com.example.what_to_play.tools;

import com.example.what_to_play.tables.Games;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class GetUserGameInfo {

    private String API_KEY;
    private String userId;
    private HttpClient client;
    private ArrayList<Games> games;


    public GetUserGameInfo(String userId) {
        this.API_KEY = "6B8FFC968253508CCCE2E5C66068A448";
        this.userId = userId;
        this.client = HttpClient.newHttpClient();
        ArrayList<Games> games = new ArrayList<>();
    }

    public void getUserGames(){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="+ API_KEY +"&steamid="+ userId +"&format=json&include_appinfo=true"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.body());
            System.out.println(root.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getGameTags(Long gameID){
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
                if(i == 0){
                    tags = tags + genre.get("description").textValue();
                }else {
                    tags = tags + ", " + genre.get("description").textValue();
                }
                i++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }


}
