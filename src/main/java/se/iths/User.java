package se.iths;

import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.time.temporal.ChronoUnit;


public class User {

    private String name;
    private int age;
    private int weight;
    private int height;
    private int fitnessScore;
    private HashMap <String, Activity> activities = new HashMap<>();
    private String latestActivityId;

    
    public User (String name){
        this.name = name;
        this.fitnessScore = 0;
    }

    public void addActivity(Activity activity) {
        fitnessScore += activity.getDistance() + (activity.getAverageSpeed() / activity.getMinutesPerKilometer()) - getDaysSinceLastActivity() / 2;
        latestActivityId = activity.getId();
        activities.put(activity.getId(), activity);
    }
    
    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public Activity getActivityById(String id) {
        return activities.get(id);
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private Activity getLatestActivity() {
        return getActivityById(latestActivityId);
    }
    public int getDaysSinceLastActivity() {
        return (getLatestActivity() == null ? 0 : (int)ChronoUnit.DAYS.between(getLatestActivity().getStartDate(), LocalDate.now()));
    }
    public int getFitnessScore() {
        return fitnessScore;
    }
}
