package SmartHomeDDD.domain.Measurement;

import SmartHomeDDD.ValueObject.MeasurementTypeUnit;
import SmartHomeDDD.ValueObject.MeasurementTypeDescription;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Tests for the {@link ImpMeasurementFactory} class to ensure that measurement types are created correctly under various conditions
 * and that appropriate exceptions are thrown when invalid parameters are provided.
 */
class ImpMeasurementFactoryTest {

    /**
     * Test to ensure that a MeasurementType can be created successfully when
     * {@link ImpMeasurementFactory#createMeasurement(MeasurementTypeDescription, MeasurementTypeUnit)}
     * is called with valid parameters. This test verifies that no exceptions are thrown during the creation process.
     */
    @Test
    void shouldCreateMeasurementType_WhenCreateMeasurementIsCalledWithValidParameters() {
        // Arrange
        MeasurementTypeDescription measurementTypeDescription = mock(MeasurementTypeDescription.class);
        MeasurementTypeUnit measurementTypeUnit = mock(MeasurementTypeUnit.class);
        ImpMeasurementFactory factory = new ImpMeasurementFactory();

        // Act
        MeasurementType result = factory.createMeasurement(measurementTypeDescription, measurementTypeUnit);

        // Assert
        assertNotNull(result);
        assertEquals(measurementTypeDescription, result.getID());
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when
     * {@link ImpMeasurementFactory#createMeasurement(MeasurementTypeDescription, MeasurementTypeUnit)}
     * is called with a null MeasurementTypeDescription parameter. This test confirms the factory's robustness in parameter validation.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateMeasurementIsCalledWithNullMeasurementTypeDescription() {
        // Arrange
        MeasurementTypeDescription measurementTypeDescription = null;
        MeasurementTypeUnit measurementTypeUnit = mock(MeasurementTypeUnit.class);
        ImpMeasurementFactory factory = new ImpMeasurementFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createMeasurement(measurementTypeDescription, measurementTypeUnit), "Factory should throw IllegalArgumentException for null MeasurementTypeDescription.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when
     * {@link ImpMeasurementFactory#createMeasurement(MeasurementTypeDescription, MeasurementTypeUnit)}
     * is called with a null MeasurementTypeUnit parameter. This test verifies the factory's careful check for null values in its arguments.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateMeasurementIsCalledWithNullMeasurementTypeUnit() {
        // Arrange
        MeasurementTypeDescription measurementTypeDescription = mock(MeasurementTypeDescription.class);
        MeasurementTypeUnit measurementTypeUnit = null;
        ImpMeasurementFactory factory = new ImpMeasurementFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createMeasurement(measurementTypeDescription, measurementTypeUnit), "Factory should throw IllegalArgumentException for null MeasurementTypeUnit.");
    }
}
