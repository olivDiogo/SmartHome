package smart_home.domain.log;

import org.junit.jupiter.api.Test;
import smart_home.value_object.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LogFactoryImplTest {

    /**
     * Test to ensure that a Log can be created successfully when createLog is called with valid parameters.
     */
    @Test
    void shouldCreateLog_WhenCreateLogIsCalledWithValidParameters() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        SensorID sensorID = mock(SensorID.class);
        LocalDateTime timeStamp = mock(LocalDateTime.class);
        ReadingValue readingValue = mock(ReadingValue.class);
        SensorTypeID description = mock(SensorTypeID.class);
        UnitID unit = mock(UnitID.class);

        LogFactoryImpl factory = new LogFactoryImpl();

        //Act
        Log result = factory.createLog(deviceID, sensorID, timeStamp, readingValue, description, unit);

        //Assert
        assertNotNull(result);
    }

    /**
     * Test to ensure that a Log can be created successfully when createLog is called with ID.
     */
    @Test
    void shouldCreateLog_WhenCreateLogIsCalledWithID() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        SensorID sensorID = mock(SensorID.class);
        LocalDateTime timeStamp = mock(LocalDateTime.class);
        ReadingValue readingValue = mock(ReadingValue.class);
        LogID logID = mock(LogID.class);
        SensorTypeID description = mock(SensorTypeID.class);
        UnitID unit = mock(UnitID.class);

        LogFactoryImpl factory = new LogFactoryImpl();

        //Act
        Log result = factory.createLog(logID, deviceID, sensorID, timeStamp, readingValue, description, unit);

        //Assert
        assertNotNull(result);
    }
}
