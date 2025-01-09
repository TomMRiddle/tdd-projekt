package se.iths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestUser {
    
    private User user;
   private Activity activity;

    @BeforeEach

    public void setup(){
        user = new User("name");
        user.setHeight(100);
        user.setAge(10);
        user.setWeight(100);
        activity = new Activity(10, "PT1H");


    }


    @Test
    public void hasAName() {

        assertEquals("name", user.getName());

    }

    @Test
    public void hasAHeight() {

        assertEquals(100, user.getHeight());

    }

    @Test
    public void hasAnAge() {

        assertEquals(10, user.getAge());

    }

    @Test
    public void hasAWeight() {

        assertEquals(100, user.getWeight());

    }

    @Test 
    public void idStoredInHashmap() {
        user.addActivity(activity);
        assertEquals(activity, user.getActivityByID("1"));
    }
}
