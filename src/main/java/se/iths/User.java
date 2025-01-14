package se.iths;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;


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
        fitnessScore += activity.getDistance() + (activity.getAverageSpeed() / activity.getMinutesPerKilometer()) - getDaysSinceLastActivity(activity.getStartDate()) / 2;
        latestActivityId = activity.getId();
        activities.put(activity.getId(), activity);
    }

    public boolean hasActivity() {
        return !activities.isEmpty();
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
    public int getDaysSinceLastActivity(LocalDate newDate) {
        return (getLatestActivity() == null ? 0 : (int)ChronoUnit.DAYS.between(getLatestActivity().getStartDate(), newDate));
    }
    public int getFitnessScore() {
        return fitnessScore;
    }

    public int getTotalDistance() {
        return activities.values().stream().mapToInt(Activity::getDistance).sum();
    }
    public double getAverageDistance() {
        return activities.values().stream().mapToInt(Activity::getDistance).average().orElseThrow(NullPointerException::new);
    }
    public void printActivities() {

        for (Activity entry: activities.values()) {
            System.out.println(entry);
        }
    }

    public void printActivityById(String id) throws NullPointerException {
        if(!activities.containsKey(id)) {
            throw new NullPointerException("Id not found");
        }
        System.out.println(activities.get(id));
    }
    public void deleteActivityById(String id) throws NullPointerException {
        if(!activities.containsKey(id)) {
            throw new NullPointerException("Cannot delete non-existing activity: Activity Id not found");
        }
        activities.remove(id);
    }

    @Override
    public String toString() {
        return "User created with:\nName: "+name+"\nAge: "+age+" years\nWeight: "+weight+" kg\nHeight: "+height+" cm";
    }
}
