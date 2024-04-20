package smart_home.service;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.domain.actuator_type.ActuatorTypeFactoryImpl;
import smart_home.persistence.mem.ActuatorTypeRepository;
import smart_home.persistence.mem.UnitRepository;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorTypeServiceImplTest {

    /**
     * Tests the instantiation of ActuatorTypeService with valid parameters.
     */
    @Test
    void shouldInstantiateActuatorTypeService_WhenParametersAreValid() {
        // Arrange
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorTypeFactoryImpl actuatorTypeFactory = mock(ActuatorTypeFactoryImpl.class);
        UnitRepository unitRepository = mock(UnitRepository.class);

        // Act
        ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
                new ActuatorTypeServiceImpl(actuatorTypeRepository, actuatorTypeFactory, unitRepository);

        // Assert
        assertNotNull(ActuatorTypeServiceImpl);
    }

    /**
     * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
     * repository is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenActuatorTypeRepositoryIsNull() {
        // Arrange
        ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ActuatorTypeServiceImpl(null, actuatorTypeFactoryDouble, unitRepositoryDouble));

        // Assert
        assertEquals("Please enter a valid actuator type repository.", exception.getMessage());
    }

    /**
     * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
     * factory is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenActuatorTypeFactoryIsNull() {
        // Arrange
        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ActuatorTypeServiceImpl(actuatorTypeRepositoryDouble, null, unitRepositoryDouble));

        // Assert
        assertEquals("Please enter a valid actuator type factory.", exception.getMessage());
    }

    /**
     * Test that the ActuatorTypeService can throw an IllegalArgumentException when the Unit
     * repository is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenUnitRepositoryIsNull() {
        // Arrange
        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ActuatorTypeServiceImpl(actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, null));

        // Assert
        assertEquals("Please enter a valid unit repository.", exception.getMessage());
    }


    /**
     * Test that the ActuatorTypeService can return the ActuatorType when the ActuatorType is created
     * and saved to the repository.
     */
    @Test
    void shouldReturnTheActuatorType_whenActuatorTypeIsCreatedAndSavedToRepository() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
                new ActuatorTypeServiceImpl(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
        when(unitRepositoryDouble.containsOfIdentity(unitID)).thenReturn(true);

        // Act
        ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(actuatorTypeName, unitID);

        // Assert
        assertEquals(actuatorType, actuatorTypeDouble);
    }

    /**
     * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
     * name is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenActuatorTypeNameIsNull() {
        // Arrange
        TypeDescription actuatorTypeName = null;
        UnitID unitID = mock(UnitID.class);

        ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
                new ActuatorTypeServiceImpl(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> ActuatorTypeServiceImpl.createActuatorType(actuatorTypeName, unitID));
    }

    /**
     * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
     * already exists.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenActuatorTypeAlreadyExists() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        when(actuatorTypeRepositoryDouble.existsOfName(actuatorTypeName)).thenReturn(true);

        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
                new ActuatorTypeServiceImpl(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> ActuatorTypeServiceImpl.createActuatorType(actuatorTypeName, unitID));
    }

    /**
     * Test save method
     */
    @Test
    void shouldReturnTheActuatorType_whenActuatorTypeIsSavedToRepository() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
                new ActuatorTypeServiceImpl(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
        when(actuatorTypeRepositoryDouble.save(actuatorTypeDouble)).thenReturn(actuatorTypeDouble);

        // Act
        ActuatorType actuatorType = ActuatorTypeServiceImpl.addActuatorType(actuatorTypeDouble);

        // Assert
        assertEquals(actuatorTypeDouble, actuatorType);
    }

    /**
     * Test find all actuator types
     */
    @Test
    void shouldReturnAllActuatorTypes_whenFindAllActuatorTypes() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
                new ActuatorTypeServiceImpl(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
        when(actuatorTypeRepositoryDouble.findAll()).thenReturn(List.of(actuatorTypeDouble));

        // Act
        List<ActuatorType> actuatorTypes = ActuatorTypeServiceImpl.getAllActuatorTypes();

        // Assert
        assertEquals(List.of(actuatorTypeDouble), actuatorTypes);
    }

    /**
     * Test find actuator by typeId
     */
    @Test
    void shouldReturnActuatorType_whenFindActuatorTypeByTypeId() {
        // Arrange
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("1");

        ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
                new ActuatorTypeServiceImpl(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
        when(actuatorTypeRepositoryDouble.ofIdentity(actuatorTypeID)).thenReturn(Optional.of(actuatorTypeDouble));

        // Act
        Optional<ActuatorType> actuatorType = ActuatorTypeServiceImpl.getActuatorTypeByID(actuatorTypeID);

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

        ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        ActuatorTypeID actuatorTypeID = null;

        String expectedMessage = "Please enter a valid sensor type ID.";

        ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
                new ActuatorTypeServiceImpl(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

        // Act + Assert
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> ActuatorTypeServiceImpl.getActuatorTypeByID(actuatorTypeID));

        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Tests if IllegalArgumentException is thrown when the ActuatorType is null.
     */
    @Test
    void shouldThrowException_whenActuatorTypeIsNull() {
        // Arrange
        ActuatorType actuatorTypeDouble = null;
        TypeDescription actuatorTypeName = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
                .thenReturn(actuatorTypeDouble);

        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

        String expectedMessage = "Please enter a valid sensor type.";

        ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
                new ActuatorTypeServiceImpl(
                        actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

        // Act + Assert
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> ActuatorTypeServiceImpl.addActuatorType(actuatorTypeDouble));

        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }
}
