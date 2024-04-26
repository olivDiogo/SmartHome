package smarthome.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.sensor_type.SensorTypeFactoryImpl;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.mem.UnitRepository;

class SensorTypeServiceImplTest {

  @Test
  void shouldInstantiateSensorTypeServiceWhenGivenValidParameters() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    UnitRepository unitRepository = mock(UnitRepository.class);
    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);

    //Act
    SensorTypeServiceImpl result = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);

    //Assert
    assertNotNull(result);
  }

  @Test
  void shouldThrowExceptionWhenSensorTypeRepositoryIsNull() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = null;
    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    UnitRepository unitRepository = mock(UnitRepository.class);
    String expectedMessage = "Sensor type repository is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenSensorTypeFactoryIsNull() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    SensorTypeFactoryImpl sensorTypeFactory = null;
    UnitRepository unitRepository = mock(UnitRepository.class);
    String expectedMessage = "Sensor type factory is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenMeasurementTypeRepositoryIsNull() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    UnitRepository unitRepository = null;
    String expectedMessage = "Unit repository is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldCreateValidSensorTypeWhenGivenValidParameters() {
    //Arrange
    TypeDescription typeDescription = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);
    SensorType sensorType = mock(SensorType.class);

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    when(sensorTypeFactory.createSensorType(typeDescription, unitID)).thenReturn(sensorType);

    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    UnitRepository unitRepository = mock(UnitRepository.class);
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    SensorType resultSensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    //Assert
    assertEquals(sensorType, resultSensorType);
  }

  @Test
  void shouldThrowExceptionWhenMeasurementIDIsInvalid() {
    //Arrange
    TypeDescription typeDescription = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);
    SensorType sensorType = mock(SensorType.class);

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    when(sensorTypeFactory.createSensorType(typeDescription, unitID)).thenReturn(sensorType);

    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    UnitRepository unitRepository = mock(UnitRepository.class);
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(false);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    String expectedMessage = "Please enter a valid measurement type.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorTypeServiceImpl.createSensorType(typeDescription, unitID));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldSaveSensorTypeWhenGivenValidParameters() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    when(sensorTypeRepository.save(sensorType)).thenReturn(sensorType);

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    UnitRepository unitRepository = mock(UnitRepository.class);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    SensorType resultSensorType = sensorTypeServiceImpl.addSensorType(sensorType);
    //Assert
    assertEquals(sensorType, resultSensorType);
  }

  @Test
  void shouldThrowExceptionWhenSavingNullSensorType() {
    //Arrange
    SensorType sensorType = null;
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    UnitRepository unitRepository = mock(UnitRepository.class);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    String expectedMessage = "Please enter a valid sensor type.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorTypeServiceImpl.addSensorType(sensorType));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldFindSensorTypeByIDWhenSensorTypeExistInRepository() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    when(sensorTypeRepository.ofIdentity(sensorTypeID)).thenReturn(Optional.of(sensorType));

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    UnitRepository unitRepository = mock(UnitRepository.class);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    SensorType resultSensorType = sensorTypeServiceImpl.getSensorTypeByID(sensorTypeID).get();
    //Assert
    assertEquals(sensorType, resultSensorType);
  }

  @Test
  void shouldThrowExceptionWhenFindingSensorTypeByNullID() {
    //Arrange
    SensorTypeID sensorTypeID = null;
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    UnitRepository unitRepository = mock(UnitRepository.class);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    String expectedMessage = "Please enter a valid sensor type ID.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorTypeServiceImpl.getSensorTypeByID(sensorTypeID));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldReturnOptionalEmptyWhenFindingSensorTypeByNonExistentID() {
    //Arrange
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    when(sensorTypeRepository.ofIdentity(sensorTypeID)).thenReturn(Optional.empty());

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    UnitRepository unitRepository = mock(UnitRepository.class);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    Optional<SensorType> resultSensorType = sensorTypeServiceImpl.getSensorTypeByID(sensorTypeID);
    //Assert
    assertTrue(resultSensorType.isEmpty());
  }

  @Test
  void shouldReturnAllSensorTypesWhenFindingAllSensorTypes() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    when(sensorTypeRepository.findAll()).thenReturn(List.of(sensorType));

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    UnitRepository unitRepository = mock(UnitRepository.class);
    List<SensorType> expectedSensorTypes = List.of(sensorType);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    List<SensorType> resultSensorTypes = sensorTypeServiceImpl.getAllSensorTypes();
    //Assert
    assertEquals(expectedSensorTypes, resultSensorTypes);
  }

  @Test
  void shouldReturnEmptyListWhenNoSensorTypesExist() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    when(sensorTypeRepository.findAll()).thenReturn(List.of());

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    UnitRepository unitRepository = mock(UnitRepository.class);
    List<SensorType> expectedSensorTypes = List.of();

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    List<SensorType> resultSensorTypes = sensorTypeServiceImpl.getAllSensorTypes();
    //Assert
    assertEquals(expectedSensorTypes, resultSensorTypes);
  }

}