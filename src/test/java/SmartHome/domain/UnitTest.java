package SmartHome.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitTest {

    @Test
    void shouldReturnUnitsForTemperatureInString() {
        // Arrange
        Unit unit = Unit.TEMPERATURE;
        String expected = "C";
        // Act
        String result = unit.getUnit();
        // Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnUnitsForUnitsInString() {
        // Arrange
        Unit unit = Unit.HUMIDITY;
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