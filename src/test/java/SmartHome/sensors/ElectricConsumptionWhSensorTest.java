package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ElectricConsumptionWhSensorTest {
    @Test
    void shouldCreateElectricConsumptionWhSensor() throws InstantiationException {
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
    void fillMockConsumptionMapWithIsOfExpectedSize() throws InstantiationException {
    //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        LocalDateTime startTime = LocalDateTime.now().minusDays(5).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        int expectedSize = (int) Duration.between(startTime, endTime).toMinutes() / 60 + 1;

    //Act
        Map<LocalDateTime, Integer> mapAllConsumption = electricConsumptionWhSensor.artificialDatabaseOfReadingsSetTo50W();
    //Assert
        assertEquals(expectedSize, mapAllConsumption.size());
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
    void fillMapWithRandomConsumptionWithValidDataShouldSpamLast5Days() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        LocalDateTime startTime = LocalDateTime.now().minusDays(5).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        //Act
        Map<LocalDateTime, Integer> mapAllConsumption = electricConsumptionWhSensor.artificialDatabaseOfReadingsSetTo50W();
        //Assert
        assertEquals(startTime, mapAllConsumption.keySet().toArray()[0]);
        assertEquals(endTime, mapAllConsumption.keySet().toArray()[mapAllConsumption.size()-1]);
    }
    @Test
    void fillMapWithArtificialConsumptionShouldHaveSameValueInAllEntries() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        int valueSet = 50;
        //Act
        Map<LocalDateTime, Integer> mapAllConsumption = electricConsumptionWhSensor.artificialDatabaseOfReadingsSetTo50W();
        //Assert
        assertTrue(mapAllConsumption.values().stream().allMatch(value -> value == valueSet));
    }
    @Test
    void shouldFilterReadingForSelectedTimeFrame() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        LocalDateTime startTime = LocalDateTime.now().minusDays(2).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        int expectedSize = (int) Duration.between(startTime, endTime).toMinutes() / 60 + 1;
        //Act
        Map<LocalDateTime, Integer> mapChosenTime = electricConsumptionWhSensor.getDataFromSelectedTimePeriod(startTime, endTime);
        //Assert
        assertEquals(expectedSize, mapChosenTime.size());
    }
    @Test
    void shouldThrowExceptionIfQueryOutsideRecordRange() throws InstantiationException {
      //Arrange
            String description = "ElectricConsumptionWh";
            CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
            SensorType sensorTypeDouble = mock(SensorType.class);
            when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
            ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

            LocalDateTime startTime = LocalDateTime.now().minusDays(8).truncatedTo(ChronoUnit.MINUTES);
            LocalDateTime endTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            String expectedMessage = "Timestamps must be within the range of the records.";
            //Act and Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () -> electricConsumptionWhSensor.getDataFromSelectedTimePeriod(startTime, endTime));
            //Assert
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
    }
    @Test
    void shouldThrowExceptionIfQueryIntoFutureData() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        LocalDateTime startTime = LocalDateTime.now().minusDays(3).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = LocalDateTime.now().plusHours(5).truncatedTo(ChronoUnit.MINUTES);
        String expectedMessage = "Timestamps must be within the range of the records.";
        //Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> electricConsumptionWhSensor.getDataFromSelectedTimePeriod(startTime, endTime));
        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    void shouldThrowExceptionIfStartTimestampIsAfterEndTimestamp() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        LocalDateTime startTime = LocalDateTime.now().plusDays(3).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        String expectedMessage = "Start timestamp must be before end timestamp.";
        //Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> electricConsumptionWhSensor.getDataFromSelectedTimePeriod(startTime, endTime));
        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldReturnConsumptionInGivenTimePeriod() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        LocalDateTime startTime = LocalDateTime.now().minusDays(2).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        int expectedSize = (int) Duration.between(startTime, endTime).toMinutes() / 60 + 1;
        double expectedConsumption = 50 * expectedSize;

        //Act
        double actualConsumption = electricConsumptionWhSensor.calculateConsumptionInWh(startTime, endTime);
        //Assert
        assertEquals(expectedConsumption, actualConsumption);
    }

    @Test
    void shouldReturnConsumptionForSelectedPeriodInValueFormat() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        LocalDateTime startTime = LocalDateTime.now().minusDays(2).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        int expectedSize = (int) Duration.between(startTime, endTime).toMinutes() / 60 + 1;
        int consumption = 50 * expectedSize;
        String expectedConsumption = "ElectricConsumptionWh{" + consumption + '}';
        //Act
        Value actualConsumption = electricConsumptionWhSensor.getValue(startTime, endTime);
        //Assert
        assertEquals(expectedConsumption, actualConsumption.toString());
    }
    @Test
    void shouldNotConsideredIncompleteReadings() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        LocalDateTime startTime = LocalDateTime.now().minusDays(2).minusMinutes(30).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        int expectedSize = (int) Duration.between(startTime, endTime).toMinutes() / 60 + 1;
        int consumption = 50 * expectedSize;
        String expectedConsumption = "ElectricConsumptionWh{" + consumption + '}';
        //Act
        Value actualConsumption = electricConsumptionWhSensor.getValue(startTime, endTime);
        //Assert
        assertEquals(expectedConsumption, actualConsumption.toString());
    }

    @Test
    void shouldReturnConsumptionInLast24HoursIfTimePeriodNotSelected() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);

        LocalDateTime startTime = LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        int expectedSize = (int) Duration.between(startTime, endTime).toMinutes() / 60 + 1;
        int expectedConsumption = 50 * expectedSize;
        String expectedConsumptionString = "ElectricConsumptionWh{" + expectedConsumption + '}';
        //Act
        Value actualConsumption = electricConsumptionWhSensor.getValue();
        //Assert
        assertEquals(expectedConsumptionString, actualConsumption.toString());
    }
}