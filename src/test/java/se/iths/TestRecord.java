package se.iths;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestRecord {
    private static Record record;

    @BeforeAll
    static void setup() {
        record = new Record(10, Duration.parse("PT1H"), "2025-10-03");
    }

    @Test
    void hasDistance() {
        assertEquals(10, record.getDistance());
    }

    @Test
    void hasDuration() {
        assertEquals(Duration.ofHours(1), record.getDuration());
    }

    @Test
    void hasStartDate() {
        assertEquals(LocalDate.parse("2025-10-03"), record.getStartDate());
    }

    @Test
    void hasStartDateNow() {
        Record recordNow = new Record(10, Duration.parse("PT1H"));
        assertEquals(LocalDate.now(), recordNow.getStartDate());
    }

    @Test
    void hasUniqueId() {
        Record record2 = new Record( 10, Duration.parse("PT1H"), "2025-10-03");
        assertNotEquals(record2.getId(), record.getId());
    }

    @Test
    void throwsExceptionWhenDurationIsNull() {
        assertThrows(NullPointerException.class, () -> new Record(10, null, "2025-10-03"));
    }

    @Test
    void throwsExceptionWhenDistanceIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Record(0, Duration.parse("PT1H"), "2025-10-03"), "Distance must be greater than 0");
    }

    @Test
    void throwsExceptionWhenDurationIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Record(10, Duration.parse("PT0H"), "2025-10-03"), "Duration must be greater than 0");
    }

    @Test
    void hasAverageSpeed() {
        assertEquals(10, record.getAverageSpeed());
    }

    @Test
    void hasMinutesPerKilometer() {
        assertEquals(6, record.getMinutesPerKilometer());
    }
}
