package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.ddd.IAssembler;
import smart_home.domain.actuator_model.ActuatorModelFactoryImpl;
import smart_home.domain.actuator_model.IActuatorModelFactory;
import smart_home.domain.repository.IActuatorModelRepository;
import smart_home.domain.repository.ISensorModelRepository;
import smart_home.domain.repository.ISensorTypeRepository;
import smart_home.domain.repository.IUnitRepository;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.domain.sensor_type.ISensorTypeFactory;
import smart_home.domain.sensor_type.SensorType;
import smart_home.domain.service.ISensorTypeService;
import smart_home.domain.service.IUnitService;
import smart_home.domain.unit.Unit;
import smart_home.mapper.SensorTypeAssembler;
import smart_home.mapper.UnitAssembler;
import smart_home.domain.sensor_model.ISensorModelFactory;
import smart_home.domain.sensor_model.SensorModelFactoryImpl;
import smart_home.domain.sensor_type.SensorTypeFactoryImpl;
import smart_home.domain.unit.IUnitFactory;
import smart_home.domain.unit.UnitFactoryImpl;
import smart_home.dto.SensorTypeDTO;
import smart_home.dto.SensorTypeDataDTO;
import smart_home.dto.UnitDTO;
import smart_home.persistence.mem.ActuatorModelRepository;
import smart_home.persistence.mem.SensorModelRepository;
import smart_home.persistence.mem.SensorTypeRepository;
import smart_home.service.SensorTypeServiceImpl;
import smart_home.service.UnitServiceImpl;
import smart_home.utils.LoadModelsAndUnit;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorModelName;
import smart_home.value_object.SensorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitID;
import smart_home.value_object.UnitSymbol;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddSensorTypeControllerTest {

  /**
   * Test if the constructor of AddSensorTypeController throws an exception when the sensorTypeService is null.
   */
  @Test
    void shouldThrowExceptionWhenSensorTypeServiceIsNull() {
        //Arrange
        ISensorTypeService sensorTypeServiceImpl = null;
        IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
        IUnitRepository unitRepository = mock(IUnitRepository.class);
        IUnitFactory unitFactory = new UnitFactoryImpl();
        IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
        IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();

        String expectedMessage = "Sensor type service is required";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

  /**
   * Test if the constructor of AddSensorTypeController throws an exception when the unitService is null.
   */
  @Test
    void shouldThrowException_WhenUnitServiceIsNull() {
        //Arrange
        ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
        ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
      IUnitRepository unitRepository = mock(IUnitRepository.class);
      ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);
      IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
      IUnitService unitServiceImpl = null;
      IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();

        String expectedMessage = "Unit service is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

  /**
   * Test if the constructor of AddSensorTypeController throws an exception when the sensorTypeAssembler is null.
   */
  @Test
  void shouldThrowExceptionWhenSensorTypeAssemblerIsNull() {
        //Arrange
      ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
      ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
      IUnitRepository unitRepository = mock(IUnitRepository.class);
      ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);        SensorTypeAssembler sensorTypeAssembler = null;
        IUnitFactory unitFactory = new UnitFactoryImpl();
      IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
      IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();

        String expectedMessage = "Sensor type assembler is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

  /**
   * Test if the constructor of AddSensorTypeController throws an exception when the unitAssembler is null.
   */
  @Test
  void shouldThrowExceptionWhenUnitAssemblerIsNull() {
        //Arrange
      ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
      ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
      IUnitRepository unitRepository = mock(IUnitRepository.class);
      ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
      IUnitFactory unitFactory = new UnitFactoryImpl();
      IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
      IAssembler<Unit, UnitDTO> unitAssembler = null;

        String expectedMessage = "Unit assembler is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

  /**
   * Test if the getSupportedUnits method returns a list of units.
   * @throws InstantiationException if an instantiation error occurs.
   */
  @Test
    void shouldReturnListOfUnitsWhenUnitsLoaded() throws InstantiationException {
        //Arrange
      ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
      ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
      IUnitRepository unitRepository = mock(IUnitRepository.class);
      ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
      IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

      IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();
        IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
      ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
      UnitDescription unitDescription = new UnitDescription("Celsius");
      UnitSymbol unitSymbol = new UnitSymbol("C");
      SensorModelName sensorModelName = new SensorModelName("name");
      ModelPath modelPath = new ModelPath("smart_home.domain.sensor.temperature_sensor");
      SensorTypeID sensorTypeID = new SensorTypeID(UUID.randomUUID().toString());
      SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath, sensorTypeID);
      when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
      Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
      when(unitRepository.findAll()).thenReturn(List.of(unit));

      IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);
        IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

        LoadModelsAndUnit loadModelsAndUnit = new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);

        List<UnitDTO> expected = unitAssembler.domainToDTO(unitRepository.findAll());


        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);
        //Act
        List<UnitDTO> actual = addSensorTypeController.getSupportedUnits();
        //Assert
        assertEquals(expected.toString(), actual.toString());

    }

  /**
   * Test if the addAndSaveSensorType method returns a SensorTypeDTO when a sensor type is added.
   * @throws InstantiationException if an instantiation error occurs.
   */
  @Test
  void shouldReturnSensorTypeDTOWhenSensorTypeAdded() throws InstantiationException {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

    IUnitFactory unitFactory = new UnitFactoryImpl();
    IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

    IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();
    IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
    ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    SensorModelName sensorModelName = new SensorModelName("name");
    ModelPath modelPath = new ModelPath("smart_home.domain.sensor.temperature_sensor");
    SensorTypeID sensorTypeID = new SensorTypeID(UUID.randomUUID().toString());
    SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath, sensorTypeID);
    when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    when(unitRepository.containsOfIdentity(unit.getID())).thenReturn(true);


    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);
    IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

    new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = unit.getID();
    AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.containsOfIdentity(sensorType.getID())).thenReturn(false);
    when(sensorTypeRepository.save(sensorType)).thenReturn(sensorType);

    String sensorTypeDescription = "Temperature";
    String supportedUnit = "Celsius";
    SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO(sensorTypeDescription, supportedUnit);
    SensorTypeDTO expectedSensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);
    //Act
    SensorTypeDTO actual = addSensorTypeController.addAndSaveSensorType(sensorTypeDataDTO);
    //Assert
    assertEquals(expectedSensorTypeDTO.toString(), actual.toString());
  }


  /**
   * Test if the addAndSaveSensorType method throws an exception when the sensor type already exists.
   * @throws InstantiationException   if an instantiation error occurs.
   */
  @Test
    void shouldThrowExceptionWhenSensorTypeAlreadyExists() throws InstantiationException {
        //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

    IUnitFactory unitFactory = new UnitFactoryImpl();
    IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

    IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();
    IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
    ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    SensorModelName sensorModelName = new SensorModelName("name");
    ModelPath modelPath = new ModelPath("smart_home.domain.sensor.temperature_sensor");
    SensorTypeID sensorTypeID = new SensorTypeID(UUID.randomUUID().toString());
    SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath, sensorTypeID);
    when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    when(unitRepository.containsOfIdentity(unit.getID())).thenReturn(true);


    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);
    IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

    new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = unit.getID();
    AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.containsOfIdentity(sensorType.getID())).thenReturn(true);

    String sensorTypeDescription = "Temperature";
    String supportedUnit = "Celsius";
    SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO(sensorTypeDescription, supportedUnit);
    SensorTypeDTO expectedSensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

        String expectedMessage = "Invalid sensor type data.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> addSensorTypeController.addAndSaveSensorType(sensorTypeDataDTO));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

  /**
   * Test if the addAndSaveSensorType method throws an exception when the sensor type data is invalid.
   * @throws InstantiationException if an instantiation error occurs.
   */
  @Test
    void shouldThrowExceptionWhenAddingSensorTypeOfUnsupportedUnit() throws InstantiationException {
        //Arrange
      ISensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
      ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
      IUnitRepository unitRepository = mock(IUnitRepository.class);
      ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
      IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

      IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();
      IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();

      ISensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

      IActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

        LoadModelsAndUnit loadModelsAndUnit = new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);

        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);

        String sensorTypeDescription = "Temperature";

        String unsupportedUnit = "UnsupportedUnit";
        SensorTypeDataDTO unsupportedSensorTypeDataDTO = new SensorTypeDataDTO(sensorTypeDescription, unsupportedUnit);

        String expectedMessage = "Invalid sensor type data.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> addSensorTypeController.addAndSaveSensorType(unsupportedSensorTypeDataDTO));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

  /**
   * Test if the addAndSaveSensorType method throws an exception when the sensor has the same description but different unit.
   * @throws InstantiationException if an instantiation error occurs.
   */
  @Test
    void shouldThrowExceptionWhenAddingSensorWithSameDescriptionButDifferentUnit() throws InstantiationException {
        //Arrange
      ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
      ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
      IUnitRepository unitRepository = mock(IUnitRepository.class);
      ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
      IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

      IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();
      IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
      ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

      ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
      UnitDescription unitDescription = new UnitDescription("Celsius");
      UnitSymbol unitSymbol = new UnitSymbol("C");
      SensorModelName sensorModelName = new SensorModelName("name");
      ModelPath modelPath = new ModelPath("smart_home.domain.sensor.temperature_sensor");
      SensorTypeID sensorTypeID = new SensorTypeID(UUID.randomUUID().toString());
      SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath, sensorTypeID);
      when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
      Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
      when(unitRepository.containsOfIdentity(unit.getID())).thenReturn(true);

      IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);
      IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

      new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);

      TypeDescription typeDescription = new TypeDescription("Temperature");
      UnitID unitID = unit.getID();
      AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);
      SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
      sensorTypeServiceImpl.addSensorType(sensorType);
      when(sensorTypeRepository.containsOfIdentity(sensorType.getID())).thenReturn(false);
      when(sensorTypeRepository.save(sensorType)).thenReturn(sensorType);

      String sensorTypeDescription = "Temperature";
      String supportedUnit = "Celsius";
      SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO(sensorTypeDescription, supportedUnit);
      SensorTypeDTO expectedSensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

      String sensorTypeDescription2 = "Temperature";
      String supportedUnit2 = "Kelvin";
      SensorTypeDataDTO sensorTypeDataDTO2 = new SensorTypeDataDTO(sensorTypeDescription2, supportedUnit2);

      String expectedMessage = "Invalid sensor type data.";
      //Act
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> addSensorTypeController.addAndSaveSensorType(sensorTypeDataDTO2));

      //Assert
      assertEquals(expectedMessage, exception.getMessage());
    }


}