package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @Test
    void shouldThrowExeptionIfStartTimestampIsEqualEndTimestamp() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);
        LocalDateTime startTimestamp = LocalDateTime.now();
        LocalDateTime endTimestamp = LocalDateTime.now();
        String expectedMessage = "Start timestamp must be before end timestamp.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> electricConsumptionWhSensor.setTimePeriodAndReturnDeltaInHours(startTimestamp, endTimestamp));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldCalculateConsumptionInWhForGivenInterval() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);
        LocalDateTime startTimestamp = LocalDateTime.now();
        LocalDateTime endTimestamp = LocalDateTime.now().plusHours(2);

        Map<LocalDateTime, Double> mapAllConsumption = new HashMap<>();
        //Outside Values
        mapAllConsumption.put(startTimestamp.minusHours(1), 1.0);
        mapAllConsumption.put(endTimestamp.plusHours(3), 2.0);

        //Inside Values
        mapAllConsumption.put(startTimestamp, 5.0);
        mapAllConsumption.put(startTimestamp.plusMinutes(30), 1.0);
        mapAllConsumption.put(startTimestamp.plusMinutes(25), 0.0);
        mapAllConsumption.put(endTimestamp, 2.0);
        int expected = 4;
        electricConsumptionWhSensor.setTimePeriodAndReturnDeltaInHours(startTimestamp, endTimestamp);
        //Act
        double result = electricConsumptionWhSensor.calculateConsumptionInWh(mapAllConsumption);
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnConsumptionValue() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        LocalDateTime startTimestamp = LocalDateTime.now();
        LocalDateTime endTimestamp = LocalDateTime.now().plusHours(1);
        electricConsumptionWhSensor.setTimePeriodAndReturnDeltaInHours(startTimestamp, endTimestamp);

        Map<LocalDateTime, Double> mapAllConsumption = new HashMap<>();
        mapAllConsumption.put(startTimestamp, 1000.0);
        mapAllConsumption.put(endTimestamp, 200.0);
        electricConsumptionWhSensor.calculateConsumptionInWh(mapAllConsumption);
        try (MockedConstruction<ElectricConsumptionWhValue> mocked = mockConstruction(ElectricConsumptionWhValue.class)) {
            //Act
            Value result = electricConsumptionWhSensor.getValue();
            //Assert
            assertTrue(mocked.constructed().contains(result));
        }
    }

}