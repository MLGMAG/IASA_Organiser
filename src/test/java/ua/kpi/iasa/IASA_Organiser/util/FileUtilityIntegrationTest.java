package ua.kpi.iasa.IASA_Organiser.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilityIntegrationTest {

    public static final String OUTPUT_FILE_NAME = "test_output.txt";
    public static final String OUTPUT_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data", OUTPUT_FILE_NAME).toString();
    public static final File OUTPUT_FILE = new File(OUTPUT_PATH);

    @Test
    public void shouldCreateFile() throws IOException {
        cleanDirectory();
        FileUtility.initFile(OUTPUT_FILE);

        assertTrue(OUTPUT_FILE.exists());
        cleanDirectory();
    }

    public static void cleanDirectory() {
        if (!OUTPUT_FILE.delete()) {
            System.out.println("Can't delete file: " + OUTPUT_FILE);
        }
    }
}
