package SmartHomeDDD.service;

import SmartHomeDDD.domain.SensorType.ImpSensorTypeFactory;
import SmartHomeDDD.domain.SensorType.SensorType;
import SmartHomeDDD.repository.UnitRepository;
import SmartHomeDDD.repository.SensorTypeRepository;
import SmartHomeDDD.valueObject.UnitID;
import SmartHomeDDD.valueObject.SensorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorTypeServiceTest {
    @Test
    void shouldInstantiateSensorTypeServiceWhenGivenValidParameters() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        //Act
        new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    }
    @Test
    void shouldThrowExceptionWhenSensorTypeRepositoryIsNull() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = null;
        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        String expectedMessage = "Please enter a valid sensor type repository.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenSensorTypeFactoryIsNull() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);
        ImpSensorTypeFactory sensorTypeFactory = null;
        UnitRepository unitRepository = mock(UnitRepository.class);
        String expectedMessage = "Please enter a valid sensor type factory.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenMeasurementTypeRepositoryIsNull() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);
        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        UnitRepository unitRepository = null;
        String expectedMessage = "Please enter a valid measurement type repository.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldCreateValidSensorTypeWhenGivenValidParameters() {
        //Arrange
        TypeDescription typeDescription = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);
        SensorType sensorType = mock(SensorType.class);

        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        when(sensorTypeFactory.createSensorType(typeDescription, unitID)).thenReturn(sensorType);

        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
        //Act
        SensorType resultSensorType = sensorTypeService.createSensorType(typeDescription, unitID);
        //Assert
        assertEquals(sensorType, resultSensorType);
    }
    @Test
    void shouldThrowExceptionWhenMeasurementIDIsInvalid() {
        //Arrange
        TypeDescription typeDescription = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);
        SensorType sensorType = mock(SensorType.class);

        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        when(sensorTypeFactory.createSensorType(typeDescription, unitID)).thenReturn(sensorType);

        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        when(unitRepository.containsOfIdentity(unitID)).thenReturn(false);

        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
        String expectedMessage = "Please enter a valid measurement type.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sensorTypeService.createSensorType(typeDescription, unitID));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldSaveSensorTypeWhenGivenValidParameters() {
        //Arrange
        SensorType sensorType = mock(SensorType.class);
        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);
        when(sensorTypeRepository.save(sensorType)).thenReturn(sensorType);

        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        UnitRepository unitRepository = mock(UnitRepository.class);

        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
        //Act
        SensorType resultSensorType = sensorTypeService.saveSensorType(sensorType);
        //Assert
        assertEquals(sensorType, resultSensorType);
    }
    @Test
    void shouldThrowExceptionWhenSavingNullSensorType() {
        //Arrange
        SensorType sensorType = null;
        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);

        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        UnitRepository unitRepository = mock(UnitRepository.class);

        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
        String expectedMessage = "Please enter a valid sensor type.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sensorTypeService.saveSensorType(sensorType));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldFindSensorTypeByIDWhenSensorTypeExistInRepository() {
        //Arrange
        SensorType sensorType = mock(SensorType.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);
        when(sensorTypeRepository.ofIdentity(sensorTypeID)).thenReturn(Optional.of(sensorType));

        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        UnitRepository unitRepository = mock(UnitRepository.class);

        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
        //Act
        SensorType resultSensorType = sensorTypeService.findSensorTypeByID(sensorTypeID).get();
        //Assert
        assertEquals(sensorType, resultSensorType);
    }
    @Test
    void shouldThrowExceptionWhenFindingSensorTypeByNullID() {
        //Arrange
        SensorTypeID sensorTypeID = null;
        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);

        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        UnitRepository unitRepository = mock(UnitRepository.class);

        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
        String expectedMessage = "Please enter a valid sensor type ID.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sensorTypeService.findSensorTypeByID(sensorTypeID));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldReturnOptionalEmptyWhenFindingSensorTypeByNonExistentID() {
        //Arrange
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);
        when(sensorTypeRepository.ofIdentity(sensorTypeID)).thenReturn(Optional.empty());

        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        UnitRepository unitRepository = mock(UnitRepository.class);

        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
        //Act
        Optional<SensorType> resultSensorType = sensorTypeService.findSensorTypeByID(sensorTypeID);
        //Assert
        assertTrue(resultSensorType.isEmpty());
    }
    @Test
    void shouldReturnAllSensorTypesWhenFindingAllSensorTypes() {
        //Arrange
        SensorType sensorType = mock(SensorType.class);
        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);
        when(sensorTypeRepository.findAll()).thenReturn(List.of(sensorType));

        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        List<SensorType> expectedSensorTypes = List.of(sensorType);

        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
        //Act
        List<SensorType> resultSensorTypes = sensorTypeService.findAllSensorTypes();
        //Assert
        assertEquals(expectedSensorTypes, resultSensorTypes);
    }
    @Test
    void shouldReturnEmptyListWhenNoSensorTypesExist() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = mock(SensorTypeRepository.class);
        when(sensorTypeRepository.findAll()).thenReturn(List.of());

        ImpSensorTypeFactory sensorTypeFactory = mock(ImpSensorTypeFactory.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        List<SensorType> expectedSensorTypes = List.of();

        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
        //Act
        List<SensorType> resultSensorTypes = sensorTypeService.findAllSensorTypes();
        //Assert
        assertEquals(expectedSensorTypes, resultSensorTypes);
    }

}