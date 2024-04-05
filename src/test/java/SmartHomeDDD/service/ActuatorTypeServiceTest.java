package SmartHomeDDD.service;

import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import SmartHomeDDD.domain.ActuatorType.ImpActuatorTypeFactory;
import SmartHomeDDD.repository.ActuatorTypeRepository;
import SmartHomeDDD.repository.UnitRepository;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.UnitID;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActuatorTypeServiceTest {
    /**
     * Test that the ActuatorTypeService can return the ActuatorType when the ActuatorType is created
     * and saved to the repository.
     */
    @Test
    public void shouldReturnTheActuatorType_whenActuatorTypeIsCreatedAndSavedToRepository() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ImpActuatorTypeFactory actuatorTypeFactoryDouble = mock(ImpActuatorTypeFactory.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeService actuatorTypeService =
                new ActuatorTypeService(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
        when(unitRepositoryDouble.containsOfIdentity(unitID)).thenReturn(true);

        // Act
        ActuatorType actuatorType = actuatorTypeService.createActuatorType(actuatorTypeName, unitID);

        // Assert
        assertEquals(actuatorType, actuatorTypeDouble);
    }

    /**
     * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
     * name is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenActuatorTypeNameIsNull() {
        // Arrange
        TypeDescription actuatorTypeName = null;
        UnitID unitID = mock(UnitID.class);

        ImpActuatorTypeFactory actuatorTypeFactoryDouble = mock(ImpActuatorTypeFactory.class);
        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeService actuatorTypeService =
                new ActuatorTypeService(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> actuatorTypeService.createActuatorType(actuatorTypeName, unitID));
    }

    /**
     * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
     * already exists.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenActuatorTypeAlreadyExists() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ImpActuatorTypeFactory actuatorTypeFactoryDouble = mock(ImpActuatorTypeFactory.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        when(actuatorTypeRepositoryDouble.existsOfName(actuatorTypeName)).thenReturn(true);

        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeService actuatorTypeService =
                new ActuatorTypeService(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> actuatorTypeService.createActuatorType(actuatorTypeName, unitID));
    }

    /**
     * Test save method
     */
    @Test
    public void shouldReturnTheActuatorType_whenActuatorTypeIsSavedToRepository() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ImpActuatorTypeFactory actuatorTypeFactoryDouble = mock(ImpActuatorTypeFactory.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeService actuatorTypeService =
                new ActuatorTypeService(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
        when(actuatorTypeRepositoryDouble.save(actuatorTypeDouble)).thenReturn(actuatorTypeDouble);

        // Act
        ActuatorType actuatorType = actuatorTypeService.saveActuatorType(actuatorTypeDouble);

        // Assert
        assertEquals(actuatorTypeDouble, actuatorType);
    }

    /**
     * Test find all actuator types
     */
    @Test
    public void shouldReturnAllActuatorTypes_whenFindAllActuatorTypes() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ImpActuatorTypeFactory actuatorTypeFactoryDouble = mock(ImpActuatorTypeFactory.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeService actuatorTypeService =
                new ActuatorTypeService(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
        when(actuatorTypeRepositoryDouble.findAll()).thenReturn(List.of(actuatorTypeDouble));

        // Act
        List<ActuatorType> actuatorTypes = actuatorTypeService.findAllActuatorTypes();

        // Assert
        assertEquals(List.of(actuatorTypeDouble), actuatorTypes);
    }

    /**
     * Test find actuator by typeId
     */
    @Test
    public void shouldReturnActuatorType_whenFindActuatorTypeByTypeId() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ImpActuatorTypeFactory actuatorTypeFactoryDouble = mock(ImpActuatorTypeFactory.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getId()).thenReturn("1");

        ActuatorTypeService actuatorTypeService =
                new ActuatorTypeService(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
        when(actuatorTypeRepositoryDouble.ofIdentity(actuatorTypeID)).thenReturn(Optional.of(actuatorTypeDouble));

        // Act
        Optional<ActuatorType> actuatorType = actuatorTypeService.findActuatorTypeByID(actuatorTypeID);

        // Assert
        assertEquals(Optional.of(actuatorTypeDouble), actuatorType);
    }

    /**
     * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
     */
    @Test
    void shouldThrowException_whenActuatorTypeIDIsNull() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ImpActuatorTypeFactory actuatorTypeFactoryDouble = mock(ImpActuatorTypeFactory.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeID actuatorTypeID = null;

        String expectedMessage = "Please enter a valid sensor type ID.";

        ActuatorTypeService actuatorTypeService =
                new ActuatorTypeService(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

        // Act + Assert
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> actuatorTypeService.findActuatorTypeByID(actuatorTypeID));

        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);

    }
}
