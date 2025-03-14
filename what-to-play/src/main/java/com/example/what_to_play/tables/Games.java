package com.example.what_to_play.tables;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
public class Games {
    @Id
    Long appId;

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHoursPlayed(int hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getAppId() {
        return appId;
    }

    public String getName() {
        return name;
    }

    public int getHoursPlayed() {
        return hoursPlayed;
    }

    public String getTags() {
        return tags;
    }

    String name;
    int hoursPlayed;
    String tags;

    public Games() {
        this.appId = null;
        this.name = null;
        this.hoursPlayed = 0;
        this.tags = null;
    }

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
