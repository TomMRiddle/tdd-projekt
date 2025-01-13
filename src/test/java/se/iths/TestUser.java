package se.iths;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestUser {
    private User user;
    private static Activity activity;
    private static Activity activity2;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @BeforeAll
    static void setupAll() {
        activity = new Activity(10, "PT1H", "2025-01-01");
        activity2 = new Activity(10, "PT1H", "2025-01-05");
    }
    @BeforeEach
    void setup(){
        System.setOut(new PrintStream(outContent));
        user = new User("name");
        user.setHeight(100);
        user.setAge(10);
        user.setWeight(100);
    }

    @AfterEach
    public void restoreStreams() {
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
        user.addActivity(activity);
        assertEquals(activity,user.getActivityById(activity.getId()));
    }
    @Test
    void hasDaysSinceLastActivity() {
        user.addActivity(activity);
        assertEquals(ChronoUnit.DAYS.between(LocalDate.parse("2025-01-01"), activity.getStartDate()), user.getDaysSinceLastActivity(activity.getStartDate()));
    }
    @Test
    void hasZeroDaysSinceLastActivityWhenNoPreviousActivity() {
        assertEquals(0, user.getDaysSinceLastActivity(activity.getStartDate()));
    }
    @Test
    void hasFitnessScoreFirstActivity() {
        user.addActivity(activity);
        assertEquals(11,user.getFitnessScore());
    }
    @Test
    void hasFitnessScoreSecondActivity() {
        user.addActivity(activity);
        user.addActivity(activity2);
        assertEquals(20,user.getFitnessScore());
    }
    @Test
    void hasTotalDistance() {
        user.addActivity(activity);
        user.addActivity(activity2);
        assertEquals(20, user.getTotalDistance());
    }
    @Test
    void hasAverageDistance() {
        user.addActivity(activity);
        user.addActivity(activity2);
        user.addActivity(new Activity(40, "PT1H", "2025-01-06"));
        assertEquals(20, user.getAverageDistance());
    }
    @Test
    void printsAllActivities() {
        user.addActivity(activity);
        user.addActivity(activity2);
        user.printActivities();
        assertEquals("Id:1, date:2025-01-01, duration:PT1H, distance:10\r\nId:2, date:2025-01-05, duration:PT1H, distance:10\r\n", outContent.toString());
    }

    @Test
    void printsDetailsWhenGivenId(){
        user.addActivity(activity);
        user.printActivityById("1");
         assertEquals( "Id:1, date:2025-01-01, duration:PT1H, distance:10\r\n", outContent.toString());

    }

}
