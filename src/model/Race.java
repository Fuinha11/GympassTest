package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Race {
    private Integer lastLap = 4;
    private List<Pilot> pilots;

    public Pilot getWinner() {
        if (pilots == null || pilots.size() == 0)
            return null;

        Pilot winner = pilots.get(0);

        for (Pilot p : pilots) {
            if (p.getLapTime(lastLap).before(winner.getLapTime(lastLap)))
                winner = p;                             //if p finishing time is before winner finishing time, replace winner
        }

        return winner;
    }

    public List<Ranking> getRanking() {
        List<Ranking> rank = new ArrayList<>();

        pilots.sort((p1, p2) -> {
            if (p1.getLapTime(lastLap) == null)
                return 1;

            if (p2.getLapTime(lastLap) == null)
                return -1;

            if (p1.getLapTime(lastLap).before(p2.getLapTime(lastLap)))
                return -1;
            else
                return 1;
        });

        Date raceEnd = pilots.get(0).getLapTime(lastLap);

        for (int i = 0; i < pilots.size(); i++) {
           rank.add(new Ranking(pilots.get(i), i + 1, raceEnd, lastLap));
        }

        return rank;
    }

    public Pilot getBestLapInRace() {
        if (pilots == null || pilots.size() == 0)
            return null;

        Pilot currentBest = pilots.get(0);

        for (Pilot p : pilots) {
            if (p.bestLap().lapTime.minus(currentBest.bestLap().lapTime).isNegative()) //if p.bestLap time is smaller then currentBest, replace it
                currentBest = p;
        }

        return currentBest;
    }

}
