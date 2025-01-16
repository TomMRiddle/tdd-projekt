package se.iths;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class TestUser {
    private String lineSeparator = System.lineSeparator();
    private User user;
    private static Record record;
    private static Record record2;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    static void setupAll() {
        record = new Record(10,Duration.parse("PT1H"), "2025-01-01");
        record2 = new Record(10, Duration.parse("PT1H"), "2025-01-05");
    }
    @BeforeEach
    void setup() {

        System.setOut(new PrintStream(outContent));
        user = new User("name");
        user.setHeight(100);
        user.setAge(10);
        user.setWeight(100);
    }


    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void hasAName() {
        assertEquals("name", user.getName());
    }

    @Test
    void hasAHeight() {

        assertEquals(100, user.getHeight());

    }

    @Test
    void hasAnAge() {

        assertEquals(10, user.getAge());

    }

    @Test
    void hasAWeight() {

        assertEquals(100, user.getWeight());

    }

    @Test
    void activityStoredInHashmap() {
        user.addRecord(record);
        assertEquals(record,user.getRecordById(record.getId()));
    }
    @Test
    void hasDaysSinceLastActivity() {
        user.addRecord(record);
        assertEquals(ChronoUnit.DAYS.between(LocalDate.parse("2025-01-01"), record.getStartDate()), user.getDaysSinceLastRecord(record.getStartDate()));
    }
    @Test
    void hasZeroDaysSinceLastActivityWhenNoPreviousActivity() {
        assertEquals(0, user.getDaysSinceLastRecord(record.getStartDate()));
    }
    @Test
    void hasFitnessScoreFirstActivity() {
        user.addRecord(record);
        assertEquals(11,user.getFitnessScore());
    }
    @Test
    void hasFitnessScoreSecondActivity() {
        user.addRecord(record);
        user.addRecord(record2);
        assertEquals(20,user.getFitnessScore());
    }
    @Test
    void hasTotalDistance() {
        user.addRecord(record);
        user.addRecord(record2);
        assertEquals(20, user.getTotalDistance());
    }
    @Test
    void hasAverageDistance() {
        user.addRecord(record);
        user.addRecord(record2);
        user.addRecord(new Record(40, Duration.parse("PT1H"), "2025-01-06"));
        assertEquals(20, user.getAverageDistance());
    }
    @Test
    void printsAllActivities() {
        user.addRecord(record);
        user.addRecord(record2);
        user.printRecords();
        assertEquals("Id: 1, Date: 2025-01-01, Duration: PT1H, Distance: 10 km" + lineSeparator + "Id: 2, Date: 2025-01-05, Duration: PT1H, Distance: 10 km" +lineSeparator, outContent.toString());
    }

    @Test
    void printsDetailsWhenGivenId(){
        user.addRecord(record);
        user.printRecordById("1");
         assertEquals( "Id: 1, Date: 2025-01-01, Duration: PT1H, Distance: 10 km" + lineSeparator, outContent.toString());
    }
    @Test
    void throwsExceptionWhenActivityIdNotFound() {
        assertThrows(NullPointerException.class, () -> user.printRecordById("99"), "Id not found");
    }

    @Test
    void deleteActivityWhenGivenId() {
        user.addRecord(record);
        user.deleteRecordById("1");
        assertNotEquals(record, user.getRecordById("1"));
    }
    @Test
    void throwsExceptionWhenDeletingNonExistingActivity() {
        assertThrows(NullPointerException.class, () -> user.deleteRecordById("99"), "Cannot delete non-existing activity: Activity Id not found");
    }

    @Test
    void hasRecordTrueWhenActivitiesExist() {
        user.addRecord(record);
        assertTrue(user.hasRecord());
    }
}
