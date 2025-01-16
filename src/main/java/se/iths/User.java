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
    private HashMap <String, Record> records = new HashMap<>();
    private String latestRecordById;

    
    public User (String name){
        this.name = name;
        this.fitnessScore = 0;
    }

    public void addRecord(Record record) {
        fitnessScore += record.getDistance() + (record.getAverageSpeed() / record.getMinutesPerKilometer()) - getDaysSinceLastRecord(record.getStartDate()) / 2;
        latestRecordById = record.getId();
        records.put(record.getId(), record);
    }

    public boolean hasRecord() {
        return !records.isEmpty();
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

    public Record getRecordById(String id) {
        return records.get(id);
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

    private Record getLatestRecord() {
        return getRecordById(latestRecordById);
    }
    public int getDaysSinceLastRecord(LocalDate newDate) {
        return (getLatestRecord() == null ? 0 : (int)ChronoUnit.DAYS.between(getLatestRecord().getStartDate(), newDate));
    }
    public int getFitnessScore() {
        return fitnessScore;
    }

    public int getTotalDistance() {
        return records.values().stream().mapToInt(se.iths.Record::getDistance).sum();
    }
    public double getAverageDistance() {
        return records.values().stream().mapToInt(se.iths.Record::getDistance).average().orElseThrow(NullPointerException::new);
    }
    public void printRecords() {

        for (Record entry: records.values()) {
            System.out.println(entry);
        }
    }

    public void printRecordById(String id) throws NullPointerException {
        if(!records.containsKey(id)) {
            throw new NullPointerException("Id not found");
        }
        System.out.println(records.get(id));
    }
    public void deleteRecordById(String id) throws NullPointerException {
        if(!records.containsKey(id)) {
            throw new NullPointerException("Cannot delete non-existing activity: Activity Id not found");
        }
        records.remove(id);
    }

    @Override
    public String toString() {
        return "User created with:\nName: "+name+"\nAge: "+age+" years\nWeight: "+weight+" kg\nHeight: "+height+" cm";
    }
}
