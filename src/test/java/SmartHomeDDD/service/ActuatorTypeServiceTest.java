package SmartHomeDDD.service;

import SmartHomeDDD.assembler.ActuatorTypeAssembler;
import SmartHomeDDD.domain.ActuatorType.ImpActuatorTypeFactory;
import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import SmartHomeDDD.repository.ActuatorTypeRepository;
import SmartHomeDDD.valueObject.TypeDescription;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActuatorTypeServiceTest {
    /**
     * Test that the ActuatorTypeService can return the ActuatorType when the ActuatorType is created and saved to the repository.
     */
    @Test
    public void shouldReturnTheActuatorType_whenActuatorTypeIsCreatedAndSavedToRepository() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);

        ImpActuatorTypeFactory actuatorTypeFactoryDouble = mock(ImpActuatorTypeFactory.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName)).thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        ActuatorTypeAssembler actuatorTypeAssemblerDouble = mock(ActuatorTypeAssembler.class);

        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, actuatorTypeAssemblerDouble);

        // Act
        ActuatorType actuatorType = actuatorTypeService.addActuatorType(actuatorTypeName);

        // Assert
        assertEquals(actuatorType, actuatorTypeDouble);
    }

    /**
     * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType name is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenActuatorTypeNameIsNull() {
        // Arrange
        TypeDescription actuatorTypeName = null;

        ImpActuatorTypeFactory actuatorTypeFactoryDouble = mock(ImpActuatorTypeFactory.class);
        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        ActuatorTypeAssembler actuatorTypeAssemblerDouble = mock(ActuatorTypeAssembler.class);

        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, actuatorTypeAssemblerDouble);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> actuatorTypeService.addActuatorType(actuatorTypeName));
    }

    /**
     * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType already exists.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenActuatorTypeAlreadyExists() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);

        ImpActuatorTypeFactory actuatorTypeFactoryDouble = mock(ImpActuatorTypeFactory.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName)).thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        when(actuatorTypeRepositoryDouble.existsOfName(actuatorTypeName)).thenReturn(true);

        ActuatorTypeAssembler actuatorTypeAssemblerDouble = mock(ActuatorTypeAssembler.class);

        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, actuatorTypeAssemblerDouble);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> actuatorTypeService.addActuatorType(actuatorTypeName));
    }
}
