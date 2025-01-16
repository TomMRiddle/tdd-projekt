package se.iths;

import java.time.Duration;
import java.time.LocalDate;

public class Record {
    private static int idCounter = 0;
    private String id;
    private int distance;
    private Duration duration;
    private LocalDate startDate;
    public Record(int distance, Duration duration, String startDate) throws IllegalArgumentException, NullPointerException {
        if(distance == 0) {
            throw new IllegalArgumentException("Distance must be greater than 0");
        }
        if(duration == null) {
            throw new NullPointerException("No duration found");
        }
        if(duration == Duration.ofMillis(0)) {
            throw new IllegalArgumentException("Duration must be greater than 0");
        }
        this.id = String.valueOf(++idCounter);
        this.distance = distance;
        this.startDate = LocalDate.parse(startDate);
        this.duration = duration;
    }
    public Record(int distance, Duration duration) {
        this(distance, duration, LocalDate.now().toString());
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

    @Override
    public String toString() {
        return "Id: "+id+", Date: "+startDate+", Duration: "+duration.toString()+", Distance: "+distance+ " km";
    }
}
