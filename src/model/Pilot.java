package model;

import java.util.Date;
import java.util.List;

public class Pilot {
    private int pilotCode;
    private String pilotName;
    private List<Lap> laps;

    public Pilot(String pilotName, int pilotCode) {
        this.pilotName = pilotName;
        this.pilotCode = pilotCode;
    }

    public int getPilotCode() {
        return pilotCode;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void addLap(Lap lap) {
        laps.add(lap);
    }

    public int totalLaps(){
        return laps.size();
    }

    public Lap bestLap() {
        if (laps == null || laps.size() == 0)
            return null;

        Lap currentBest = laps.get(0);

        for (Lap l : laps) {
            if (l.lapTime.minus(currentBest.lapTime).isNegative()) //if l time is smaller then currentBest, replace it
                currentBest = l;
        }

        return bestLap();
    }

    public Date getLapTime(int lap) {
        if (laps == null || laps.size() == 0)
            return null;

        for (Lap l : laps) {
            if (l.lapNumber == lap)
                return l.finishTime; //find the finishing time for a given lap
        }

        return null; //if pilot did not finish said lap return null
    }

    public Float getAverageSpeed() {
        Float sum = 0.0F;

        for (Lap l : laps) {
            sum += l.averageSpeed;
        }

        return sum/laps.size();
    }
}
