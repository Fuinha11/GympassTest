package model;

import java.time.Duration;
import java.util.Date;

public class Ranking {
    private Pilot pilot;
    private Integer position;
    private Duration timeAfter;
    private boolean completedRace = true;

    public Ranking(Pilot pilot, Integer position, Date raceEnd, int lastLap) {
        this.pilot = pilot;
        this.position = position;
        if (position == 1)   //if this is the winner his time after is 0
            timeAfter = Duration.ZERO;
        else {
            try {       //avoiding null pointers
                this.timeAfter = Duration.between(pilot.getLapTime(lastLap).toInstant(), raceEnd.toInstant());
            } catch (Exception e) {
                timeAfter = Duration.ZERO;
                completedRace = false;
            }
        }
    }

    public Pilot getPilot() {
        return pilot;
    }

    public Integer getPosition() {
        return position;
    }

    public Duration getTimeAfter() {
        return timeAfter;
    }

    public boolean completedRace() {
        return completedRace;
    }
}
