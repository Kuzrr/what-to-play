package com.example.what_to_play.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepo extends JpaRepository<Games, Long> {
}
