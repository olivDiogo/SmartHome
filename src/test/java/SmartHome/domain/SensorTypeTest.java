package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeTest {


    /**
     * Tests the instantiation of the SensorType
     * when the description is null.
     */
    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        // Arrange
        String strDescription = null;

        String expectedMessage = "Invalid arguments";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorType(strDescription, Unit.TEMPERATURE));

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the instantiation of the SensorType
     * when the description is empty.
     */
    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        // Arrange
        String strDescription = "";

        String expectedMessage = "Invalid arguments";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorType(strDescription, Unit.TEMPERATURE));

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
    void shouldReturnDescriptionWhenGetDescriptionIsCalled() throws InstantiationException {
        // Arrange
        String strDescription = "Temperature";

        SensorType sensorType = new SensorType(strDescription, Unit.TEMPERATURE);

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
    void shouldReturnUnitWhenGetUnitIsCalled() throws InstantiationException {
        // Arrange
        String strDescription = "Temperature";

        SensorType sensorType = new SensorType(strDescription, Unit.TEMPERATURE);

        // Act
        Unit result = sensorType.getUnit();

        // Assert
        assertEquals(Unit.TEMPERATURE, result);
    }
    @Test
    void shouldThrowExceptionWhenUnitIsNull() {
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