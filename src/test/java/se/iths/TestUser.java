package se.iths;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class TestUser {
    private final String lineSeparator = System.lineSeparator();
    private User user;
    private static Record record;
    private static Record record2;
    private static Record record3;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private static FileStorage fileStorage;

    @BeforeAll
    static void setupAll() {
        fileStorage = mock(FileStorage.class);
        record = new Record(10,Duration.parse("PT1H"), "2025-01-01");
        record2 = new Record(10, Duration.parse("PT1H"), "2025-01-05");
        record3 = new Record(40, Duration.parse("PT1H"), "2025-01-06");
    }

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(outContent));
        user = new User("name", fileStorage);
        user.setHeight(100);
        user.setAge(10);
        user.setWeight(100);
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void hasUser() {
        assertEquals("Name: name\nAge: 10 years\nWeight: 100 kg\nHeight: 100 cm", user.toString());
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
    void recordStored() throws IOException {
        user.addRecord(record);
        verify(fileStorage, atLeast(1)).createRecord(record.getId(), record.getDistance(), (int) record.getDuration().toSeconds(), record.getStartDate());
        when(fileStorage.readRecord(record.getId())).thenReturn(record);
        assertEquals(record,user.getRecordById(record.getId()));
    }

    @Test
    void hasDaysSinceLastRecord() throws IOException {
        assertEquals(ChronoUnit.DAYS.between(LocalDate.parse("2025-01-01"), record.getStartDate()), user.getDaysSinceLastRecord(record.getStartDate()));
    }

    @Test
    void hasZeroDaysSinceLastRecordWhenNoPreviousRecord() throws IOException {
            assertEquals(0, user.getDaysSinceLastRecord(record.getStartDate()));
    }

    @Test
    void hasFitnessScoreFirstRecord() throws IOException {
        user.addRecord(record);
        when(fileStorage.readRecord(record.getId())).thenReturn(record);
        assertEquals(11,user.getFitnessScore());
    }

    @Test
    void hasFitnessScoreSecondRecord() throws IOException {
        user.addRecord(record);
        user.addRecord(record2);
        when(fileStorage.readRecord(record.getId())).thenReturn(record);
        when(fileStorage.readRecord(record2.getId())).thenReturn(record2);
        assertEquals(20, user.getFitnessScore());
    }

    @Test
    void hasTotalDistance() throws IOException {
        when(fileStorage.readRecord(record.getId())).thenReturn(record);
        when(fileStorage.readRecord(record2.getId())).thenReturn(record2);
        when(fileStorage.getRecordIDs()).thenReturn(new ArrayList<String>(List.of(record.getId(), record2.getId())));
        assertEquals(20, user.getTotalDistance());
    }

    @Test
    void hasAverageDistance() throws IOException {
        when(fileStorage.readRecord(record.getId())).thenReturn(record);
        when(fileStorage.readRecord(record2.getId())).thenReturn(record2);
        when(fileStorage.readRecord(record3.getId())).thenReturn(record3);
        when(fileStorage.getRecordIDs()).thenReturn(new ArrayList<String>(List.of(record.getId(), record2.getId(), record3.getId())));
        assertEquals(20, user.getAverageDistance());
    }

    @Test
    void printsAllRecords() throws IOException {
        when(fileStorage.getRecordIDs()).thenReturn(Arrays.asList(record.getId(),record2.getId()));
        when(fileStorage.readRecord(record.getId())).thenReturn(record);
        when(fileStorage.readRecord(record2.getId())).thenReturn(record2);
        user.printRecords();
        assertEquals("Id: 1, Date: 2025-01-01, Duration: PT1H, Distance: 10 km" + lineSeparator + "Id: 2, Date: 2025-01-05, Duration: PT1H, Distance: 10 km" +lineSeparator, outContent.toString());
    }

    @Test
    void printsDetailsWhenGivenIdExist() throws IOException {
        when(fileStorage.readRecord(record.getId())).thenReturn(record);
        user.printRecordById(record.getId());
        assertEquals( "Id: 1, Date: 2025-01-01, Duration: PT1H, Distance: 10 km" + lineSeparator, outContent.toString());
    }

    @Test
    void getRecordByIdThrowsExceptionWhenRecordIdNotFound() throws IOException {
        when(fileStorage.readRecord("99")).thenReturn(null);
        assertThrows(NullPointerException.class, () -> user.getRecordById("99"));
    }

    @Test
    void printRecordByIdThrowsExceptionWhenRecordIdNotFound() throws IOException {
            when(fileStorage.readRecord("99")).thenReturn(null);
            assertThrows(NullPointerException.class, () -> user.printRecordById("99"));
    }

    @Test
    void deleteRecordWhenGivenId() throws IOException {
            user.deleteRecordById(record.getId());
            verify(fileStorage).deleteRecord(record.getId());
    }

    /**
     * No CUT!
     * assumes that the FileStorage class being mocked throws the exception, avoids redundant calls to readRecord.
     */
    @Test
    void throwsExceptionWhenDeletingNonExistingRecord() throws IOException {
        doThrow(NullPointerException.class).when(fileStorage).deleteRecord("99");
        try {
            user.deleteRecordById("99");
            fail("Expected exception not thrown");
        } catch (NullPointerException e) {
            assertEquals(NullPointerException.class, e.getClass());
        }
    }

    @Test
    void hasRecordTrueWhenRecordsExist() {
        when(fileStorage.getRecordIDs()).thenReturn(new ArrayList<String>(List.of("1")));
        assertTrue(user.hasRecord());
    }
    @Test
    void hasRecordFalseWhenRecordsNotExist() {
        when(fileStorage.getRecordIDs()).thenReturn(new ArrayList<String>());
        assertFalse(user.hasRecord());
    }

    @Test
    void hasRecordsFilteredByDistance() throws IOException {
        when(fileStorage.getRecordIDs()).thenReturn(Arrays.asList(record.getId(),record2.getId(), record3.getId()));
        when(fileStorage.readRecord(record.getId())).thenReturn(record);
        when(fileStorage.readRecord(record2.getId())).thenReturn(record2);
        when(fileStorage.readRecord(record3.getId())).thenReturn(record3);
        int min = 0;
        int max = 10;
        user.printRecordsFilteredByDistance(min,max);
        assertEquals("Id: 1, Date: 2025-01-01, Duration: PT1H, Distance: 10 km" + lineSeparator + "Id: 2, Date: 2025-01-05, Duration: PT1H, Distance: 10 km" +lineSeparator, outContent.toString());
    }

    @Test
    void hasMessageWhenNoFilteredRecordsExist() throws IOException {
        when(fileStorage.getRecordIDs()).thenReturn(Arrays.asList(record.getId(),record2.getId(), record3.getId()));
        when(fileStorage.readRecord(record.getId())).thenReturn(record);
        when(fileStorage.readRecord(record2.getId())).thenReturn(record2);
        when(fileStorage.readRecord(record3.getId())).thenReturn(record3);
        int min = 11;
        int max = 39;
        user.printRecordsFilteredByDistance(min,max);
        assertEquals("No records found between the filter parameters" + lineSeparator, outContent.toString());
    }
}
