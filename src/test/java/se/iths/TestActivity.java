package se.iths;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestActivity {
    private static Activity activity;

    @BeforeAll
    static void setup() {
        activity = new Activity(10, Duration.parse("PT1H"), "2025-10-03");
    }

    @Test
    void hasDistance() {
        assertEquals(10, activity.getDistance());
    }

    @Test
    void hasDuration() {
        assertEquals(Duration.ofHours(1), activity.getDuration());
    }

    @Test
    void hasStartDate() {
        assertEquals(LocalDate.parse("2025-10-03"), activity.getStartDate());
    }

    @Test
    void hasStartDateNow() {
        Activity activityNow = new Activity(10, Duration.parse("PT1H"));
        assertEquals(LocalDate.now(), activityNow.getStartDate());
    }
    @Test
    void hasUniqueId() {
        Activity activity2 = new Activity( 10, Duration.parse("PT1H"), "2025-10-03");
        assertNotEquals(activity2.getId(), activity.getId());
    }
    @Test
    void throwsExceptionWhenDurationIsNull() {
        assertThrows(NullPointerException.class, () -> new Activity(10, null, "2025-10-03"));
    }
    @Test
    void throwsExceptionWhenDistanceIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Activity(0, Duration.parse("PT1H"), "2025-10-03"), "Distance must be greater than 0");
    }
    @Test
    void hasAverageSpeed() {
        assertEquals(10,activity.getAverageSpeed());
    }
    @Test
    void hasMinutesPerKilometer() {
        assertEquals(6,activity.getMinutesPerKilometer());
    }
}
