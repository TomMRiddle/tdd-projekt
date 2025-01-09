package se.iths;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Activity {
    private static int idCounter = 0;
    private String id;
    private int distance;
    private Duration duration;
    private LocalDate startDate;
    public Activity(int distance, String duration, String startDate) throws IllegalArgumentException {
        if(distance == 0) {
            throw new IllegalArgumentException("Distance must be greater than 0");
        }
        this.id = String.valueOf(++idCounter);
        this.distance = distance;
        this.startDate = LocalDate.parse(startDate);
        this.duration = Duration.parse(duration);
    }
    public Activity(int distance, String duration) {
        this(distance,duration, LocalDate.now().toString());
    }
    public int getDistance() {
        return distance;
    }
    public Duration getDuration() {
        return duration;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public String getId() {
        return id;
    }
    public int getAverageSpeed() {
        return distance/(int)duration.toHours();
    }
    public int getMinutesPerKilometer() {
        return (int)duration.toMinutes()/distance;
    }
}
