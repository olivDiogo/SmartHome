package smarthome.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import smarthome.domain.log.ILogFactory;
import smarthome.domain.log.Log;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;

import java.time.LocalDateTime;



@Component
public class DataLoader implements CommandLineRunner {


  private final ILogRepository logRepository;
  private final ILogFactory logFactory;

  @Autowired
  public DataLoader(ILogRepository logRepository, ILogFactory logFactory) {
    this.logRepository = logRepository;
    this.logFactory = logFactory;
  }

  @Override
  public void run(String... args) throws Exception {
    // Create log entries
    DeviceID deviceID = new DeviceID("123");
    SensorID sensorID = new SensorID("123");
    LocalDateTime localDateTime = LocalDateTime.of(2023, 5, 10, 14, 0);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("Temperature");
    UnitID unit = new UnitID("C");
    Log log1 = logFactory.createLog(deviceID, sensorID, localDateTime, value, description, unit);
    logRepository.save(log1);
  }
}
