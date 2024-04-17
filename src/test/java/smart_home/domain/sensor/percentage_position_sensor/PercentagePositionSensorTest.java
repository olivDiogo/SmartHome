package smart_home.domain.sensor.percentage_position_sensor;


import org.junit.jupiter.api.Test;
import smart_home.value_object.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Test cases for the PercentagePositionSensor class.
 */
class PercentagePositionSensorTest {


    @Test
    void shouldInstantiatePercentagePositionSensor_whenConstructorArgumentsAreValid() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        //Act
        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Assert
        assertNotNull(percentagePositionSensor);
    }

    /**
     * Test to verify with mock that an IllegalArgumentException is thrown when ModelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = null;
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
        assertEquals("ModelPath is required", thrown.getMessage());

    }

    /**
     * Test to verify with mock that an IllegalArgumentException is thrown when SensorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
        assertEquals("SensorName is required", thrown.getMessage());
    }

    /**
     * Test to verify with mock that an IllegalArgumentException is thrown when SensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = null;

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
        assertEquals("SensorTypeID is required", thrown.getMessage());
    }

    /**
     * Test to verify with mock that an IllegalArgumentException is thrown when DeviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        //Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
        assertEquals("DeviceID is required", thrown.getMessage());
    }


    /**
     * Test to verify that the correct value is returned when getValue is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetValueIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        PercentagePositionSensorValue result = percentagePositionSensor.getValue();
        // Assert
        assertNotNull(result);

    }

    /**
     * Test to verify with mock that the correct ID is returned when getID is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetIDIsCalled() {
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        SensorID sensorID = mock(SensorID.class);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

        // Act
        SensorID result = percentagePositionSensor.getID();

        // Assert
        assertNotNull(result);
    }

    /**
     * Test to verify that the correct SensorTypeID is returned when getSensorTypeID is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetSensorTypeIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorTypeID result = percentagePositionSensor.getSensorTypeID();

        // Assert
        assertNotNull(result);

    }
    /**
     * Test to verify that the correct DeviceID is returned when getDeviceID is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetDeviceIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        DeviceID result = percentagePositionSensor.getDeviceID();

        // Assert
        assertNotNull(result);
    }

    /**
     * Test to verify with mock that the correct ModelPath is returned when getModelPath is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetModelPathIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        ModelPath result = percentagePositionSensor.getModelPath();

        // Assert
        assertNotNull(result);
    }

    /**
     * Test to verify with mock that the correct SensorName is returned when getName is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetNameIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorName result = percentagePositionSensor.getName();

        // Assert
        assertNotNull(result);

    }


    @Test
    void shouldInstantiatePercentagePositionSensor_WhenSensorIDIsValid () {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("Percentage");

        SensorID sensorID = mock(SensorID.class);

        //Act
        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

        //Assert
        assertNotNull(percentagePositionSensor);
    }

    @Test
    void shouldReturnFalse_WhenInstancesAreNotEqual() {
        //Arrange
        DeviceID deviceID1 = mock(DeviceID.class);
        ModelPath modelPath1 = mock(ModelPath.class);
        SensorName sensorName1 = mock(SensorName.class);
        SensorTypeID sensorTypeID1 = mock(SensorTypeID.class);
        when(sensorTypeID1.getID()).thenReturn("Percentage");
        SensorID sensorID1 = mock(SensorID.class);

        DeviceID deviceID2 = mock(DeviceID.class);
        ModelPath modelPath2 = mock(ModelPath.class);
        SensorName sensorName2 = mock(SensorName.class);
        SensorTypeID sensorTypeID2 = mock(SensorTypeID.class);
        when(sensorTypeID2.getID()).thenReturn("Percentage");
        SensorID sensorID2 = mock(SensorID.class);

        PercentagePositionSensor percentagePositionSensor1 = new PercentagePositionSensor(deviceID1, modelPath1, sensorTypeID1, sensorName1, sensorID1);
        PercentagePositionSensor percentagePositionSensor2 = new PercentagePositionSensor(deviceID2, modelPath2, sensorTypeID2, sensorName2, sensorID2);

        //Act
        boolean result = percentagePositionSensor1.equals(percentagePositionSensor2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnPercentagePositionSensorHashCode_WhenHashCodeMethodIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("Percentage");
        SensorID sensorID = mock(SensorID.class);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

        //Act
        int result = percentagePositionSensor.hashCode();

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnPercentagePositionSensorInString_WhenToStringMethodIsCalled () {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("Percentage");
        SensorID sensorID = mock(SensorID.class);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

        //Act
        String result = percentagePositionSensor.toString();

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldInstantiatePercentagePositionSensor_WhenValidateSensorIDIsValid () {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("Percentage");

        SensorID sensorID = mock(SensorID.class);

        //Act
        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

        //Assert
        assertNotNull(percentagePositionSensor);
    }

    @Test
    void shouldInstantiatePercentagePositionSensor_WhenValidateSensorIDIsNotValid () {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("Percentage");

        SensorID sensorID = mock(SensorID.class);

        //Act
        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

        //Assert
        assertNotNull(percentagePositionSensor);
    }

    /** test equals false */

    @Test
    void shouldReturnFalse_WhenComparingObjectWithNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        SensorID sensorID = mock(SensorID.class);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

        //Act
        boolean result = percentagePositionSensor.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenValidateSensorIDIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        SensorID sensorID = null;

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID));
        assertEquals("SensorID cannot be null.", thrown.getMessage());
    }
}