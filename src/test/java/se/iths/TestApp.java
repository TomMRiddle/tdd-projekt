package se.iths;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static se.iths.App.createActivity;
import static se.iths.App.createUser;


public class TestApp {
    
    public static User user;
    private static Activity activity;
    private static ByteArrayOutputStream outputStream;
    @BeforeAll
    static void setupBeforeAll() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        activity = new Activity(10,Duration.parse("PT1H"), "2025-01-01");
        user = new User("name");
        user.setHeight(100);
        user.setAge(10);
        user.setWeight(100);
    }

    @Test
    void testCreateActivity() {

        // Simulate user input
        String simulatedInput = "10\n01:00:00\n2025-10-03\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        // Run the main method
        createActivity();

        // Verify the output
        String output = outputStream.toString();
        assertTrue(output.contains("Distance: 10 km"));
        assertTrue(output.contains("Duration: PT1H"));
        assertTrue(output.contains("Date: 2025-10-03"));
    }
    
    @Test
    void testCreateUser(){
        String simulatedInput = "Alice\n170\n70\n30\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        // Run the method
        createUser();

        // Verify the output
        String output = outputStream.toString();
        assertTrue(output.contains("Name: Alice"), "Output should contain 'Name: Alice'");
        assertTrue(output.contains("Age: 30 years"), "Output should contain 'Age: 30'");
        assertTrue(output.contains("Weight: 70 kg"), "Output should contain 'Weight: 70 kg'");
        assertTrue(output.contains("Height: 170 cm"), "Output should contain 'Height: 170'");
        
    }
}
