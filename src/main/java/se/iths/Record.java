package se.iths;

import java.time.Duration;
import java.time.LocalDate;

public class Record {
    private static int idCounter = 0;
    private String id;
    private double distance;
    private Duration duration;
    private LocalDate startDate;
    public Record(double distance, Duration duration, String startDate) throws IllegalArgumentException, NullPointerException {
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
    public Record(double distance, Duration duration) {
        this(distance, duration, LocalDate.now().toString());
    }
    public double getDistance() {
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
    public double getAverageSpeed() {
        return distance/(int)duration.toHours();
    }
    public double getMinutesPerKilometer() {
        return duration.toMinutes()/distance;
    }

    @Override
    public String toString() {
        return "Id: "+id+", Date: "+startDate+", Duration: "+duration.toString()+", Distance: "+distance+ " km";
    }
}
