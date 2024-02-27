package SmartHome.controller;

import SmartHome.domain.CatalogueSensors;
import SmartHome.domain.Unit;
import SmartHome.dto.SensorTypeDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddSensorTypeControllerTest {
    @Test
    void shouldReturnAllSupportedUnits() throws InstantiationException {
        // Arrange
        CatalogueSensors catalogue = new CatalogueSensors("config.properties");
        AddSensorTypeController controller = new AddSensorTypeController(catalogue);
        int result = Unit.getAllSupportedUnits().size();
        // Act
        List<String> units = controller.getAllSupportedUnits();
        // Assert
        assertEquals(result, units.size());
    }
    @Test
    void shouldAddSensorTypeIfOfSupportedUnitType() throws InstantiationException {
        // Arrange
        CatalogueSensors catalogue = new CatalogueSensors("config.properties");
        AddSensorTypeController controller = new AddSensorTypeController(catalogue);
        // Act
        Optional<SensorTypeDTO> sensorTypeDTO = controller.addSensorType("Water Temperature", "Temperature");
        // Assert
        assertTrue(sensorTypeDTO.isPresent());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenNullCatalogue() {
        // Arrange
        CatalogueSensors catalogue = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(catalogue));
    }
    @Test
    void shouldReturnOptionalEmptyIfUnitNotSupported() throws InstantiationException {
        // Arrange
        CatalogueSensors catalogue = new CatalogueSensors("config.properties");
        AddSensorTypeController controller = new AddSensorTypeController(catalogue);
        // Act
        Optional<SensorTypeDTO> sensorTypeDTO = controller.addSensorType("Water Pressure", "Pressure");
        // Assert
        assertTrue(sensorTypeDTO.isEmpty());
    }

}