package SmartHomeDDD.domain.Unit;

import SmartHomeDDD.valueObject.UnitDescription;
import SmartHomeDDD.valueObject.UnitSymbol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

/**
 * Tests for the {@link UnitFactoryImpl} class to ensure that measurement types are created correctly under various conditions
 * and that appropriate exceptions are thrown when invalid parameters are provided.
 */
class ImpUnitFactoryTest {

    /**
     * Test to ensure that a MeasurementType can be created successfully when
     * {@link UnitFactoryImpl#createMeasurement(UnitDescription, UnitSymbol)}
     * is called with valid parameters. This test verifies that no exceptions are thrown during the creation process.
     */
    @Test
    void shouldCreateMeasurementType_WhenCreateMeasurementIsCalledWithValidParameters() {
        // Arrange
        UnitDescription unitDescription = mock(UnitDescription.class);
        UnitSymbol unitSymbol = mock(UnitSymbol.class);
        UnitFactoryImpl factory = new UnitFactoryImpl();

        // Act
        Unit result = factory.createMeasurement(unitDescription, unitSymbol);

        // Assert
        assertNotNull(result);

    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when
     * {@link UnitFactoryImpl#createMeasurement(UnitDescription, UnitSymbol)}
     * is called with a null MeasurementTypeDescription parameter. This test confirms the factory's robustness in parameter validation.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateMeasurementIsCalledWithNullMeasurementTypeDescription() {
        // Arrange
        UnitDescription unitDescription = null;
        UnitSymbol unitSymbol = mock(UnitSymbol.class);
        UnitFactoryImpl factory = new UnitFactoryImpl();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createMeasurement(unitDescription, unitSymbol), "Factory should throw IllegalArgumentException for null MeasurementTypeDescription.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when
     * {@link UnitFactoryImpl#createMeasurement(UnitDescription, UnitSymbol)}
     * is called with a null MeasurementTypeUnit parameter. This test verifies the factory's careful check for null values in its arguments.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateMeasurementIsCalledWithNullMeasurementTypeUnit() {
        // Arrange
        UnitDescription unitDescription = mock(UnitDescription.class);
        UnitSymbol unitSymbol = null;
        UnitFactoryImpl factory = new UnitFactoryImpl();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createMeasurement(unitDescription, unitSymbol), "Factory should throw IllegalArgumentException for null MeasurementTypeUnit.");
    }
}
