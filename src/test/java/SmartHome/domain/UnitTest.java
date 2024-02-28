package SmartHome.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitTest {
    @Test
    void shouldReturnTypeForTemperatureInString() {
        // Arrange
        Unit unit = Unit.Temperature;
        String expected = "Temperature";
        // Act
        String result = unit.getType();
        // Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnUnitsForTemperatureInString() {
        // Arrange
        Unit unit = Unit.Temperature;
        String expected = "C";
        // Act
        String result = unit.getUnit();
        // Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnTypeForHumidityInString() {
        // Arrange
        Unit unit = Unit.Humidity;
        String expected = "Humidity";
        // Act
        String result = unit.getType();
        // Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnUnitsForUnitsInString() {
        // Arrange
        Unit unit = Unit.Humidity;
        String expected = "%";
        // Act
        String result = unit.getUnit();
        // Assert
        assertEquals(expected, result);
    }
@Test
    void shouldReturnAllUnitsPresent() {
        // Arrange
        int expected = Unit.values().length;
        // Act
        List<String> result = Unit.getAllSupportedUnits();
        // Assert
        assertEquals(expected, result.size());
    }

    /**
     * Test to check if the method getType returns the correct type for DewPoint
      */
    @Test
    void shouldReturnTypeForDewPointInString() {
        // Arrange
        Unit unit = Unit.DewPoint;
        String expected = "DewPoint";
        // Act
        String result = unit.getType();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to check if the method getUnit returns the correct unit for DewPoint
     */
    @Test
    void shouldReturnUnitsForDewPointInString() {
        // Arrange
        Unit unit = Unit.DewPoint;
        String expected = "C";
        // Act
        String result = unit.getUnit();
        // Assert
        assertEquals(expected, result);
    }

}