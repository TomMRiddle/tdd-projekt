package se.iths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestUser {
    
    private User user;

    @BeforeEach

    public void setup(){
        User user = new User("name");
    }


    @Test
    public void hasAName() {

        assertEquals("name", user.getName());

    }

}
