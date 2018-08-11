package model;

import java.sql.Time;
import java.time.Duration;

public class Lap {
    public Integer lapNumber;
    public Duration lapTime;
    public Time finishTime;
    public float averageSpeed;

    public Lap(Integer lapNumber, Duration lapTime, Time finishTime, float averageSpeed) {
        this.lapNumber = lapNumber;
        this.lapTime = lapTime;
        this.finishTime = finishTime;
        this.averageSpeed = averageSpeed;
    }
}
