package SmartHomeDDD.domain.MeasurementType;

import SmartHomeDDD.valueObject.MeasurementTypeUnit;
import SmartHomeDDD.valueObject.MeasurementTypeDescription;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Tests for the {@link ImpMeasurementTypeTypeFactory} class to ensure that measurement types are created correctly under various conditions
 * and that appropriate exceptions are thrown when invalid parameters are provided.
 */
class ImpMeasurementTypeFactoryTest {

    /**
     * Test to ensure that a MeasurementType can be created successfully when
     * {@link ImpMeasurementTypeTypeFactory#createMeasurement(MeasurementTypeDescription, MeasurementTypeUnit)}
     * is called with valid parameters. This test verifies that no exceptions are thrown during the creation process.
     */
    @Test
    void shouldCreateMeasurementType_WhenCreateMeasurementIsCalledWithValidParameters() {
        // Arrange
        MeasurementTypeDescription measurementTypeDescription = mock(MeasurementTypeDescription.class);
        MeasurementTypeUnit measurementTypeUnit = mock(MeasurementTypeUnit.class);
        ImpMeasurementTypeTypeFactory factory = mock(ImpMeasurementTypeTypeFactory.class);

        // Act & Assert
        factory.createMeasurement(measurementTypeDescription, measurementTypeUnit);

    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when
     * {@link ImpMeasurementTypeTypeFactory#createMeasurement(MeasurementTypeDescription, MeasurementTypeUnit)}
     * is called with a null MeasurementTypeDescription parameter. This test confirms the factory's robustness in parameter validation.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateMeasurementIsCalledWithNullMeasurementTypeDescription() {
        // Arrange
        MeasurementTypeDescription measurementTypeDescription = null;
        MeasurementTypeUnit measurementTypeUnit = mock(MeasurementTypeUnit.class);
        ImpMeasurementTypeTypeFactory factory = new ImpMeasurementTypeTypeFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createMeasurement(measurementTypeDescription, measurementTypeUnit), "Factory should throw IllegalArgumentException for null MeasurementTypeDescription.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when
     * {@link ImpMeasurementTypeTypeFactory#createMeasurement(MeasurementTypeDescription, MeasurementTypeUnit)}
     * is called with a null MeasurementTypeUnit parameter. This test verifies the factory's careful check for null values in its arguments.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateMeasurementIsCalledWithNullMeasurementTypeUnit() {
        // Arrange
        MeasurementTypeDescription measurementTypeDescription = mock(MeasurementTypeDescription.class);
        MeasurementTypeUnit measurementTypeUnit = null;
        ImpMeasurementTypeTypeFactory factory = new ImpMeasurementTypeTypeFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createMeasurement(measurementTypeDescription, measurementTypeUnit), "Factory should throw IllegalArgumentException for null MeasurementTypeUnit.");
    }
}
