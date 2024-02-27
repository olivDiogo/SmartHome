package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeTest {

    /**
     * Tests the instantiation of the SensorType
     * when the description is valid.
     *
     * @throws InstantiationException if the description is null
     */
    @Test
    void whenDescriptionIsValid_thenInstantiateSensorType() throws InstantiationException {
        // Arrange
        String strDescription = "Temperature";

        // Act
        new SensorType(strDescription, Unit.Temperature);

        // Assert
    }

    /**
     * Tests the instantiation of the SensorType
     * when the description is null.
     */
    @Test
    void whenDescriptionIsNull_thenThrowsException() {
        // Arrange
        String strDescription = null;

        String expectedMessage = "Invalid arguments";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorType(strDescription, Unit.Temperature));

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the instantiation of the SensorType
     * when the description is empty.
     */
    @Test
    void whenDescriptionIsEmpty_thenThrowsException() {
        // Arrange
        String strDescription = "";

        String expectedMessage = "Invalid arguments";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorType(strDescription, Unit.Temperature));

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the method getDescription
     *
     * @throws InstantiationException if the description is null
     */
    @Test
    void getDescription() throws InstantiationException {
        // Arrange
        String strDescription = "Temperature";

        SensorType sensorType = new SensorType(strDescription, Unit.Temperature);

        // Act
        String result = sensorType.getDescription();

        // Assert
        assertEquals(strDescription, result);
    }

    /**
     * Tests the method getUnit
     *
     * @throws InstantiationException if the description is null
     */
    @Test
    void getUnit() throws InstantiationException {
        // Arrange
        String strDescription = "Temperature";

        SensorType sensorType = new SensorType(strDescription, Unit.Temperature);

        // Act
        Unit result = sensorType.getUnit();

        // Assert
        assertEquals(Unit.Temperature, result);
    }
    @Test
    void whenUnitIsNull_thenThrowsException() {
        // Arrange
        Unit unit = null;

        String expectedMessage = "Invalid arguments";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorType("Temperature", unit));

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}