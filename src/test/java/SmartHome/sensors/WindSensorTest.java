package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WindSensorTest {

    /**
     * Testing WindSensor constructor with valid description
     * @throws InstantiationException
     */
    @Test
    void newValidWindSensor() throws  InstantiationException{
        //Arrange
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);

        //Act
        WindSensor windSensor = new WindSensor(catalogueDouble);
    }

    /**
     * Testing WindSensor constructor with null argument
     * @throws InstantiationException
     */
    @Test
    void newInvalidWindSensor() {
        //Arrange
        CatalogueSensor mockCatalogue = mock(CatalogueSensor.class);
        when(mockCatalogue.getSensorType("WindSpeedAndDirection")).thenReturn(null);
        //Act + Assert
        assertThrows(InstantiationException.class, () -> {
            new WindSensor(mockCatalogue);
        });
    }
    /**
    getSensorType Method:

    Objective: Ensure it returns the correct SensorType that was initialized in the constructor.
    Test Case:
    Verify that after initializing a WindSensor instance with a CatalogueSensor, calling getSensorType returns a SensorType with the description "WindSpeedAndDirection".
    getValue Method:

    Objective: Test the functionality and reliability of the getValue method.
    Test Cases:
    Value Type: Ensure the method returns an instance of the expected value type, likely WindSensorValue or whatever class you're using for the value.
    Wind Speed Range: Verify the wind speed is within the expected range (e.g., 0 to 200 km/h). This ensures the randomness does not produce values outside realistic bounds.
    Wind Direction Validity: Check that the wind direction is one of the 8 cardinal points and that it properly randomizes among these options.
    Repeatability: Call getValue multiple times to ensure it produces different values, showing randomness works as intended.
    */

    /**
     * Testing getSensorType method with correct sensor type
     * @throws InstantiationException
     */
    @Test
    void testSensorType() throws InstantiationException {
        //Arrange
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);
        WindSensor windSensor = new WindSensor(catalogueDouble);

        //Act
        SensorType result = windSensor.getSensorType();

        //Assert
        assertEquals(sensorTypeDouble, result);
    }

    /**
     * Testing getSensorType method with incorrect sensor type
     * @throws InstantiationException
     */

    @Test
    void testWrongSensorType() throws InstantiationException {
        //Arrange
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorType sensorTypeWrong = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);
        when(catalogueDouble.getSensorType("Temperature")).thenReturn(sensorTypeWrong);
        WindSensor windSensor = new WindSensor(catalogueDouble);

        //Act
        SensorType result = windSensor.getSensorType();

        //Assert
        assertNotEquals(sensorTypeWrong, result);
    }

    /**
     * Testing getValue method
     * @throws InstantiationException
     */
    @Test
    void getValue() throws InstantiationException {
        //Arrange
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);
        WindSensor windSensor = new WindSensor(catalogueDouble);

        //Act
        WindSensorValue value = (WindSensorValue) windSensor.getValue();

        //Assert
        assertNotNull(value);
        assertTrue(value instanceof WindSensorValue); //TODO: check this line
    }
    /**
     * Testing getValue method speed is within bounds
     * @throws InstantiationException
     */

    @Test
    void getValueSpeed() throws InstantiationException {
        //Arrange
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);
        WindSensor windSensor = new WindSensor(catalogueDouble);

        //Act
        WindSensorValue value = (WindSensorValue) windSensor.getValue();

        //Assert
        assertTrue(value.getSpeed() >= 0 && value.getSpeed() <= 408);
    }

    /**
     * Testing getValue method direction is within bounds
     */
    @Test
    void getValueDirection() throws InstantiationException {
        //Arrange
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);
        WindSensor windSensor = new WindSensor(catalogueDouble);

        //Act
        WindSensorValue value = (WindSensorValue) windSensor.getValue();

        //Assert
        String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        assertTrue(value.getDirection().equals(directions[0]) || value.getDirection().equals(directions[1]) || value.getDirection().equals(directions[2]) || value.getDirection().equals(directions[3]) || value.getDirection().equals(directions[4]) || value.getDirection().equals(directions[5]) || value.getDirection().equals(directions[6]) || value.getDirection().equals(directions[7]));
    }

    /**
     * Testing toString method
     */
    @Test
    void testToString() {
        //Arrange
        String windSensorValue = "100 N";
        WindSensorValue windSensorValueDouble = mock(WindSensorValue.class);
        when(windSensorValueDouble.toString()).thenReturn(windSensorValue);

        //Act
        String result = windSensorValueDouble.toString();

        //Assert
        assertEquals("100 N", result);
    }
}