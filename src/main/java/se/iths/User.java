package se.iths;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class User {

    private String name;
    private int age;
    private int weight;
    private int height;
    private int fitnessScore;
    private HashMap <String, Record> records = new HashMap<>();
    private String latestRecordById;
    private FileStorage fileStorage;
    
    public User (String name, FileStorage fileStorage) {
        this.fileStorage = fileStorage;
        this.name = name;
        this.fitnessScore = 0;
    }

    public void addRecord(Record record) throws IOException {
        fitnessScore += record.getDistance() + (record.getAverageSpeed() / record.getMinutesPerKilometer()) - getDaysSinceLastRecord(record.getStartDate()) / 2;
        latestRecordById = record.getId();
        fileStorage.createRecord(record.getId(), record.getDistance(), (int) record.getDuration().toSeconds(), record.getStartDate());
    }

    public boolean hasRecord() {
        return !fileStorage.getRecordIDs().isEmpty();
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

    public Record getRecordById(String id) throws IOException {
        return fileStorage.readRecord(id);
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

    private Record getLatestRecord() throws IOException {
        return fileStorage.readRecord(latestRecordById);
    }
    public int getDaysSinceLastRecord(LocalDate newDate) throws IOException {
        return (getLatestRecord() == null ? 0 : (int)ChronoUnit.DAYS.between(getLatestRecord().getStartDate(), newDate));
    }
    public int getFitnessScore() {
        return fitnessScore;
    }

    public int getTotalDistance() throws IOException {
        int distance = 0;
        List<String> recordIDs = fileStorage.getRecordIDs();
        for(String recordID : recordIDs) {
            distance += fileStorage.readRecord(recordID).getDistance();
        }
        return distance;
    }
    public double getAverageDistance() throws IOException {
        double distance = 0;
        List<String> recordIDs = fileStorage.getRecordIDs();
        for(String recordID : recordIDs) {
            distance += fileStorage.readRecord(recordID).getDistance();
        }
        return distance/recordIDs.size();
    }
    public void printRecords() throws IOException {
        List<String> recordIDs = fileStorage.getRecordIDs();
        for (String recordID : recordIDs) {
            System.out.println(fileStorage.readRecord(recordID));
        }
    }

    public void printRecordById(String id) throws IOException {
        System.out.println(fileStorage.readRecord(id));
    }

    public void deleteRecordById(String id) throws IOException {
        fileStorage.deleteRecord(id);
    }

    public void printFilteredRecords(double min, double max) throws IOException {

    }

    @Override
    public String toString() {
        return "User created with:\nName: "+name+"\nAge: "+age+" years\nWeight: "+weight+" kg\nHeight: "+height+" cm";
    }
}
