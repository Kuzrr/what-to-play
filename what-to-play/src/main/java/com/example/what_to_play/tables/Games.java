package com.example.what_to_play.tables;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Games {
    @Id
    Long gameId;
    String name;
    @Column(name = "hours_played")
    int hoursPlayed;
    String tags;

}
