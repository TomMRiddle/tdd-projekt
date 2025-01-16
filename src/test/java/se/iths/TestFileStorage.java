package se.iths;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

public class TestFileStorage {
    @Mock
    private FileStorage fileStorage;

    @BeforeEach
    public void setUp() {
        fileStorage = mock(FileStorage.class);
    }
}
