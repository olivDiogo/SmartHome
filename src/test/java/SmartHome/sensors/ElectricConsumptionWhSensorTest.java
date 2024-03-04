package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ElectricConsumptionWhSensorTest {
    @Test
    void shouldCreateElectricConsumptionWhSensorWithValidData() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        //Act
        new ElectricConsumptionWhSensor(catalogueDouble);
    }
    @Test
    void shouldThrowExceptionIfSensorTypeUnsupported() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(null);
        String expectedMessage = "SensorType with description 'ElectricConsumptionWh' does not exist.";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ElectricConsumptionWhSensor(catalogueDouble));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldReturnSensorType() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);
        //Act
        SensorType result = electricConsumptionWhSensor.getSensorType();
        //Assert
        assertEquals(sensorTypeDouble, result);
    }
    @Test
    void shouldSetTimePeriodOfConsumption () throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        int hoursOffset = 1;
        LocalDateTime startTimestamp = LocalDateTime.now();
        LocalDateTime endTimestamp = LocalDateTime.now().plusHours(hoursOffset);
        //Act
        Long result = electricConsumptionWhSensor.setTimePeriodAndReturnDeltaInHours(startTimestamp, endTimestamp);
        //Assert
        assertEquals(hoursOffset, result);
    }
    @Test
    void shouldThrowExeptionIfStartTimestampIsAfterEndTimestamp() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);
        LocalDateTime startTimestamp = LocalDateTime.now();
        LocalDateTime endTimestamp = LocalDateTime.now().minusHours(1);
        String expectedMessage = "Start timestamp must be before end timestamp.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> electricConsumptionWhSensor.setTimePeriodAndReturnDeltaInHours(startTimestamp, endTimestamp));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}