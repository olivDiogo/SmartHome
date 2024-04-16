package smart_home.domain.sensor.temperature_sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureSensorValueTest {

    /**
     * Test if the constructor is properly created.
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void shouldInstantiateTemperatureSensorValue(){
        //Arrange
        double temperature = 10.5;

        //Act
        TemperatureSensorValue result = new TemperatureSensorValue(temperature);

        //Assert
        assertNotNull(result);
    }

    /**
     * Test if the constructor throws an exception when the temperature is lower than the minimum.
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void shouldThrowExceptionWhenTemoreatureIsLowerThanMinimum(){

        //Arrange
        double temperature = -300.0;

        String expectedMessage = "Temperature value must be above or equal to -273.15";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensorValue(temperature));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test if the getTemperature method returns the correct temperature.
     */
    @Test
    void shouldReturnStringValueOfTemperature(){

        //Arrange
        double temperature = 10.5;

        TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(temperature);

        String expectedValue = "10.5";

        //Act
        String result = new TemperatureSensorValue(temperature).toString();

        //Assert
        assertEquals(expectedValue, result.toString());
    }

    /**
     * Test if the equals method returns true when the temperature values are equal.
     */
    @Test
    void shouldReturnTrue_whenTemperatureSensorValuesAreEqual(){

        //Arrange
        double temperature = 10.5;

        TemperatureSensorValue temperatureSensorValue1 = new TemperatureSensorValue(temperature);
        TemperatureSensorValue temperatureSensorValue2 = new TemperatureSensorValue(temperature);

        //Act
        boolean result = temperatureSensorValue1.equals(temperatureSensorValue2);

        //Assert
        assertTrue(result);
    }

    /**
     * Test if the equals method returns false when the temperature values are not equal.
     */
    @Test
    void shouldReturnFalse_whenTemperatureSensorValuesAreNotEqual(){

        //Arrange
        double temperature1 = 10.5;
        double temperature2 = 20.5;

        TemperatureSensorValue temperatureSensorValue1 = new TemperatureSensorValue(temperature1);
        TemperatureSensorValue temperatureSensorValue2 = new TemperatureSensorValue(temperature2);

        //Act
        boolean result = temperatureSensorValue1.equals(temperatureSensorValue2);

        //Assert
        assertFalse(result);
    }

    /**
     * Test if the equals method returns false when the object is not a TemperatureSensorValue.
     */
    @Test
    void shouldReturnFalse_whenObjectIsNotTemperatureSensorValue(){

        //Arrange
        double temperature = 10.5;

        TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(temperature);

        //Act
        boolean result = temperatureSensorValue.equals(new Object());

        //Assert
        assertFalse(result);
    }

    /**
     * Test if the hashCode method returns the value for TemperatureSensorValue.
     */
    @Test
    void shouldReturnHashCodeOfTemperatureSensorValue(){

        //Arrange
        double temperature = 10.5;

        TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(temperature);

        //Act
        int result = temperatureSensorValue.hashCode();

        //Assert
        assertEquals(temperatureSensorValue.hashCode(), result);
    }


}