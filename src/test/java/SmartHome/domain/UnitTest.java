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


}