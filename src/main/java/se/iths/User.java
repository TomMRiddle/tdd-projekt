package se.iths;

import java.util.HashMap;

public class User {

    private String name;
    private int age;
    private int weight;
    private int height;
    private HashMap <String, Activity> activities = new HashMap<>();

    
    public User (String name){
        this.name = name;
    }

    public void addActivity(Activity activity) {
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

    public Activity getActivityByID(String id) {
        return activities.get(id);
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
