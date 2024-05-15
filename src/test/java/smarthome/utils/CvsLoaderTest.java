package smarthome.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CvsLoaderTest {

  private CvsLoader loadDefaultConfiguration = new CvsLoader();

  @Test
  void testLoadDefaultsFromCSVWithValidFile(@TempDir Path tempDir) throws IOException {

    Path csvFile = tempDir.resolve("test.csv");
    Files.write(csvFile, "col1,col2\nval1,val2".getBytes());

    List<String[]> result = loadDefaultConfiguration.loadDefaultsFromCVS(csvFile.toString());

    assertEquals(2, result.size());
    assertArrayEquals(new String[]{"col1", "col2"}, result.get(0));
    assertArrayEquals(new String[]{"val1", "val2"}, result.get(1));
  }

  @Test
  void testLoadDefaultsFromCSVWithEmptyFile(@TempDir Path tempDir) throws IOException {
    Path csvFile = tempDir.resolve("empty.csv");
    Files.write(csvFile, new byte[0]);

    List<String[]> result = loadDefaultConfiguration.loadDefaultsFromCVS(csvFile.toString());

    assertTrue(result.isEmpty());
  }

  @Test
  void testLoadDefaultsFromCSVWithInvalidPath() {
    List<String[]> result = loadDefaultConfiguration.loadDefaultsFromCVS("invalid/path/to/csv");

    assertTrue(result.isEmpty());
  }

  @Test
  void testLoadDefaultsFromCSVWithSpecialCharacters(@TempDir Path tempDir) throws IOException {
    Path csvFile = tempDir.resolve("special.csv");
    Files.write(csvFile, "col1,\"col2 with, comma\"\n\"val1\nnewline\",val2".getBytes());

    List<String[]> result = loadDefaultConfiguration.loadDefaultsFromCVS(csvFile.toString());

    assertEquals(2, result.size());
    assertArrayEquals(new String[]{"col1", "col2 with, comma"}, result.get(0));
    assertArrayEquals(new String[]{"val1\nnewline", "val2"}, result.get(1));
  }

}