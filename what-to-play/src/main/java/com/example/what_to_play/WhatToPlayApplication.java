package com.example.what_to_play;


import com.example.what_to_play.tools.GetUserGameInfo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WhatToPlayApplication {

	public static void main(String[] args) {
		//SpringApplication.run(WhatToPlayApplication.class, args);
		System.out.println("Welcome to what-to-play!");

		GetUserGameInfo info = new GetUserGameInfo("76561198158132974");

		String tag = info.getGameTags(1086940L);
		info.getUserGames();
		System.out.println(tag);


	}

}
