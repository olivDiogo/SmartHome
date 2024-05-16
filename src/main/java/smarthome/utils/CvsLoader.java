package smarthome.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CvsLoader {

  /**
   * Method to load CSV files from the given path
   *
   * @param cvsName
   * @return
   */
  public List<String[]> loadCVSFileToListOfStrings(String cvsName) {
    List<String[]> rows = new ArrayList<>();
    try (CSVReader reader = new CSVReader(new FileReader(cvsName))) {
      rows = reader.readAll();
    } catch (IOException | CsvException e) {
      return rows;
    }
    return rows;
  }

}
