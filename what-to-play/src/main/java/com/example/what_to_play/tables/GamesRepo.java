package com.example.what_to_play.tables;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesRepo extends JpaRepository<Games, Long> {

    List<Games> findByHoursPlayedBetweenAndTagsContaining(int min, int max, String tags, Sort sort);

    List<Games> findByTagsContainingIgnoreCase(String tag, Sort sort);

    @Query("SELECT g.tags FROM Games g")
    List<String> findAllTags();

    @Query("SELECT g FROM Games g WHERE g.hoursPlayed BETWEEN :minHours AND :maxHours AND g.tags LIKE %:tag%")
    List<Games> findByHoursAndTag(@Param("minHours") int minHours,
                                 @Param("maxHours") int maxHours,
                                 @Param("tag") String tag, Sort sort);
}
