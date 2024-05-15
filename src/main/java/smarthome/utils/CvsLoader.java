package smarthome.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CvsLoader {

  public static List<String[]> loadDefaultsFromCVS(String cvsName) {
    List<String[]> rows = new ArrayList<>();
    try (CSVReader reader = new CSVReader(new FileReader(cvsName))) {
      rows = reader.readAll();
    } catch (IOException | CsvException e) {
      return rows;
    }
    return rows;
  }

}
