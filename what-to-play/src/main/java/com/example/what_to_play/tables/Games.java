package com.example.what_to_play.tables;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Games {
    @Id
    Long appId;
    String name;
    int hoursPlayed;
    String tags;

    public Games(Long appId, String name, int hoursPlayed, String tags) {
        this.appId = appId;
        this.name = name;
        this.hoursPlayed = hoursPlayed;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return appId + " - " + name + " - " + hoursPlayed + "h - " + tags;
    }
}
