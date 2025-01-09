package se.iths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;


public class TestActivity {
    private Activity activity;
    @BeforeEach
    public void setup() {
        activity = new Activity(10, "PT1H", "2025-10-03");
    }
    @Test
    public void hasDistance() {
        assertEquals(10, activity.getDistance());
    }
    @Test
    public void hasDuration() {
        assertEquals(Duration.ofHours(1), activity.getDuration());
    }
    @Test
    public void hasStartDate() {
        assertEquals(LocalDate.parse("2025-10-03"), activity.getStartDate());
    }
    @Test
    public void hasStartDateNow() {
        Activity activityNow = new Activity(10, "PT1H");
        assertEquals(LocalDate.now(), activityNow.getStartDate());
    }

    }
    @Test
    public void throwsExceptionNoDuration() {
        assertThrows(Exception.class, () -> new Activity( 10, null, "2025-10-03"));
    }
    @Test
    public void throwsExceptionNoDistance() {
        assertThrows(IllegalArgumentException.class, () -> new Activity( 0, "PT1H", "2025-10-03"), "Distance must be greater than 0");
    }

}