package se.iths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;


class TestUser {
    
    private User user;
   private Activity activity;

    @BeforeEach

    void setup(){
        user = new User("name");
        user.setHeight(100);
        user.setAge(10);
        user.setWeight(100);
        activity = new Activity(10, "PT1H", "2025-01-01");
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
        assertEquals(8, user.getDaysSinceLastActivity());
    }
    @Test
    void hasZeroDaysSinceLastActivityWhenNoActivity() {
        assertEquals(0, user.getDaysSinceLastActivity());
    }
    @Test
    void hasFitnessScore() {
        user.addActivity(activity);
        assertEquals(11,user.getFitnessScore());
    }
}
