package smarthome.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.dto.LogDTO;

class LogAssemblerTest {

  /**
   * Test if the domainToDTO method returns a LogDTO object when the log is valid.
   */
  @Test
  void shouldReturnALogDTO_WhenGivenALog() {
    //Arrange
    String logID = "1";
    String deviceID = "1";
    String sensorID = "1";
    String sensorTypeID = "1";
    String reading = "1";
    String timestamp = "2021-10-10 10:10:10";
    String unitID = "1";

    Log log = mock(Log.class);

    when(log.getID()).thenReturn(mock(LogID.class));
    when(log.getID().toString()).thenReturn(logID);

    when(log.getDeviceID()).thenReturn(mock(DeviceID.class));
    when(log.getDeviceID().toString()).thenReturn(deviceID);

    when(log.getSensorID()).thenReturn(mock(SensorID.class));
    when(log.getSensorID().toString()).thenReturn(sensorID);

    when(log.getDescription()).thenReturn(mock(SensorTypeID.class));
    when(log.getDescription().toString()).thenReturn(sensorTypeID);

    when(log.getReadingValue()).thenReturn(mock(ReadingValue.class));
    when(log.getReadingValue().toString()).thenReturn(reading);

    when(log.getTimeStamp()).thenReturn(mock(LocalDateTime.class));
    when(log.getTimeStamp().toString()).thenReturn(timestamp);

    when(log.getUnit()).thenReturn(mock(UnitID.class));
    when(log.getUnit().toString()).thenReturn(unitID);

    LogAssembler logAssembler = new LogAssembler();

    String expected =
        logID + " " + deviceID + " " + sensorID + " " + sensorTypeID + " " + reading + " "
            + timestamp + " " + unitID;

    //Act
    LogDTO logDTO = logAssembler.domainToDTO(log);

    //Assert
    assertEquals(expected, logDTO.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the log is null.
   */
  @Test
  void shouldThrowAnIllegalArgumentException_WhenGivenANullLog() {
    //Arrange
    Log log = null;
    LogAssembler logAssembler = new LogAssembler();

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logAssembler.domainToDTO(log));

    assertEquals("Log is required", exception.getMessage());
  }

  /**
   * Test if the domainToDTO method returns a list of LogDTO objects when the list of logs is
   * valid.
   */
  @Test
  void shouldReturnAListOfLogDTO_WhenGivenAListOfLogs() {
    //Arrange
    String logID = "1";
    String deviceID = "1";
    String sensorID = "1";
    String sensorTypeID = "1";
    String reading = "1";
    String timestamp = "2021-10-10 10:10:10";
    String unitID = "1";

    Log log = mock(Log.class);
    ReadingValue readingValue = mock(ReadingValue.class);

    when(log.getID()).thenReturn(mock(LogID.class));
    when(log.getID().toString()).thenReturn(logID);

    when(log.getDeviceID()).thenReturn(mock(DeviceID.class));
    when(log.getDeviceID().toString()).thenReturn(deviceID);

    when(log.getSensorID()).thenReturn(mock(SensorID.class));
    when(log.getSensorID().toString()).thenReturn(sensorID);

    when(log.getDescription()).thenReturn(mock(SensorTypeID.class));
    when(log.getDescription().toString()).thenReturn(sensorTypeID);

    when(log.getReadingValue()).thenReturn(mock(ReadingValue.class));
    when(log.getReadingValue().toString()).thenReturn(reading);

    when(log.getTimeStamp()).thenReturn(mock(LocalDateTime.class));
    when(log.getTimeStamp().toString()).thenReturn(timestamp);

    when(log.getUnit()).thenReturn(mock(UnitID.class));
    when(log.getUnit().toString()).thenReturn(unitID);

    LogAssembler logAssembler = new LogAssembler();

    String expected =
        logID + " " + deviceID + " " + sensorID + " " + sensorTypeID + " " + reading + " "
            + timestamp + " " + unitID;

    //Act
    LogDTO logDTO = logAssembler.domainToDTO(log);

    //Assert
    assertEquals(expected, logDTO.toString());
  }

  /**
   * Test when the list of logs contains objects.
   */
  @Test
  void shouldReturnANewLogDTOList_whenGivenALogList() {
    //Arrange
    String logID = "1";
    String deviceID = "1";
    String sensorID = "1";
    String sensorTypeID = "1";
    String reading = "1";
    String timestamp = "2021-10-10 10:10:10";
    String unitID = "1";

    Log log = mock(Log.class);
    ReadingValue readingValue = mock(ReadingValue.class);

    when(log.getID()).thenReturn(mock(LogID.class));
    when(log.getID().toString()).thenReturn(logID);

    when(log.getDeviceID()).thenReturn(mock(DeviceID.class));
    when(log.getDeviceID().toString()).thenReturn(deviceID);

    when(log.getSensorID()).thenReturn(mock(SensorID.class));
    when(log.getSensorID().toString()).thenReturn(sensorID);

    when(log.getDescription()).thenReturn(mock(SensorTypeID.class));
    when(log.getDescription().toString()).thenReturn(sensorTypeID);

    when(log.getReadingValue()).thenReturn(mock(ReadingValue.class));
    when(log.getReadingValue().toString()).thenReturn(reading);

    when(log.getTimeStamp()).thenReturn(mock(LocalDateTime.class));
    when(log.getTimeStamp().toString()).thenReturn(timestamp);

    when(log.getUnit()).thenReturn(mock(UnitID.class));
    when(log.getUnit().toString()).thenReturn(unitID);

    List<Log> logs = List.of(log);

    LogAssembler logAssembler = new LogAssembler();

    LogDTO logDTO = new LogDTO(logID, deviceID, sensorID, sensorTypeID, reading, timestamp, unitID);

    List<LogDTO> expected = List.of(logDTO);

    //Act
    List<LogDTO> result = logAssembler.domainToDTO(logs);

    //Assert
    assertEquals(expected.toString(), result.toString());
  }


}




