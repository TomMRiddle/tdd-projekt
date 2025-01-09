package se.iths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestActivity {
    private Activity activity;

    @BeforeEach
    void setup() {
        activity = new Activity(10, "PT1H", "2025-10-03");
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
        Activity activityNow = new Activity(10, "PT1H");
        assertEquals(LocalDate.now(), activityNow.getStartDate());
    }
    @Test
    void hasUniqueId() {
        Activity activity2 = new Activity( 10, "PT1H", "2025-10-03");
        assertNotEquals(activity2.getId(), activity.getId());
    }
    @Test
    void throwsExceptionWhendurationIsNull() {
        assertThrows(NullPointerException.class, () -> new Activity(10, null, "2025-10-03"));
    }
    @Test
    void throwsExceptionWhenDistanceIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Activity(0, "PT1H", "2025-10-03"), "Distance must be greater than 0");
    }
}
