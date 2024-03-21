package SmartHomeDDD.domain.SensorType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.ValueObject.MeasurementTypeDescription;

import java.util.List;

import org.mockito.MockedConstruction;

public class ImpSensorTypeFactoryTest {

    /**
     * Test to ensure that a SensorType can be created successfully
     */
    @Test
    public void shouldCreateSensorType_whenAttributesAreValid() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        MeasurementTypeDescription unitDescriptionDouble = mock(MeasurementTypeDescription.class);

        try(MockedConstruction<SensorType> sensorTypeDouble = mockConstruction(SensorType.class,(mock, context) -> {
            TypeDescription actualTypeDescription = (TypeDescription) context.arguments().get(0);
            when(mock.getSensorTypeName()).thenReturn(actualTypeDescription);

        })) {
            ImpSensorTypeFactory impSensorTypeFactory = new ImpSensorTypeFactory();

            // Act
            SensorType sensorType = impSensorTypeFactory.createSensorType(typeDescriptionDouble, unitDescriptionDouble);

            // Assert
            List<SensorType> sensorTypeList = sensorTypeDouble.constructed();
            assertEquals(1, sensorTypeList.size());
            assertEquals(typeDescriptionDouble, sensorType.getSensorTypeName());
        }
    }
}
