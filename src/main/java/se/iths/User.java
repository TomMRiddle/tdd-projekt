package se.iths;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
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
        return getRecordById(latestRecordById);
    }
    public int getDaysSinceLastRecord(LocalDate newDate) throws IOException {
        return (getLatestRecord() == null ? 0 : (int)ChronoUnit.DAYS.between(getLatestRecord().getStartDate(), newDate));
    }
    public int getFitnessScore() {
        return fitnessScore;
    }

    public int getTotalDistance() {
        //refactor to use fileStorage
        return records.values().stream().mapToInt(se.iths.Record::getDistance).sum();
    }
    public double getAverageDistance() {
        AtomicReference<Double> distance = new AtomicReference<>((double) 0);
        fileStorage.getRecordIDs().forEach(recordId -> {
            distance.updateAndGet(v -> {
                try {
                    return (double) (v + fileStorage.readRecord(recordId).getDistance());
                } catch (IOException e) {
                    throw new NullPointerException(e.getMessage());
                }
            });
        });
        return distance.get()/fileStorage.getRecordIDs().size();
    }
    public void printRecords() {
        fileStorage.getRecordIDs().stream().forEach(recordId -> {
            try {
                System.out.println(fileStorage.readRecord(recordId));
            } catch (IOException e) {
                throw new NullPointerException(e.getMessage());
            }
        });
    }

    public void printRecordById(String id) {
        try {
            System.out.println(fileStorage.readRecord(id));
        } catch (IOException e) {
            throw new NullPointerException("Id not found");
        }
    }
    public void deleteRecordById(String id) throws NullPointerException {
        try {
            fileStorage.deleteRecord(id);
        } catch (IOException e) {
            throw new NullPointerException("Cannot delete non-existing activity: Activity Id not found");
        }
    }

    @Override
    public String toString() {
        return "User created with:\nName: "+name+"\nAge: "+age+" years\nWeight: "+weight+" kg\nHeight: "+height+" cm";
    }
}
