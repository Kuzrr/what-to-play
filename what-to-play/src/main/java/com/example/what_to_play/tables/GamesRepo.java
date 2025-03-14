package com.example.what_to_play.tables;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesRepo extends JpaRepository<Games, Long> {

    List<Games> findByHoursPlayedBetween(int minHours, int maxHours, Sort sort);

    List<Games> findByTagsContainingIgnoreCase(String tag, Sort sort);

}
