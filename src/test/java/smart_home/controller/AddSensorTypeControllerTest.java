package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator_model.ActuatorModelFactoryImpl;
import smart_home.domain.actuator_model.IActuatorModelFactory;
import smart_home.domain.repository.ISensorModelRepository;
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
import smart_home.persistence.mem.UnitRepository;
import smart_home.service.SensorTypeServiceImpl;
import smart_home.service.UnitServiceImpl;
import smart_home.utils.LoadModelsAndUnit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddSensorTypeControllerTest {
    @Test
    void shouldThrowExceptionWhenSensorTypeServiceIsNull() {
        //Arrange
        SensorTypeServiceImpl sensorTypeServiceImpl = null;
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
        UnitRepository unitRepository = new UnitRepository();
        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
        UnitAssembler unitAssembler = new UnitAssembler();
        String expectedMessage = "Valid SensorTypeService is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenUnitServiceIsNull() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
        UnitServiceImpl unitServiceImpl = null;
        UnitAssembler unitAssembler = new UnitAssembler();
        String expectedMessage = "Valid UnitService is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenSensorTypeAssemblerIsNull() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);        SensorTypeAssembler sensorTypeAssembler = null;
        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
        UnitAssembler unitAssembler = new UnitAssembler();
        String expectedMessage = "Valid SensorTypeAssembler is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenUnitAssemblerIsNull() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
        UnitAssembler unitAssembler = null;
        String expectedMessage = "Valid UnitAssembler is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturnListOfUnitsWhenUnitsLoaded() throws InstantiationException {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

        LoadModelsAndUnit loadModelsAndUnit = new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);

        List<UnitDTO> expected = unitAssembler.domainToDTO(unitRepository.findAll());

        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);
        //Act
        List<UnitDTO> actual = addSensorTypeController.getSupportedUnits();
        //Assert
        assertEquals(expected.toString(), actual.toString());
    }
    @Test
    void shouldReturnSensorTypeDTOWhenSensorTypeAdded() throws InstantiationException {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

        LoadModelsAndUnit loadModelsAndUnit = new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);

        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);

        String supportedUnit = "Celsius";
        String sensorTypeDescription = "Temperature";
        SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO(sensorTypeDescription, supportedUnit);


        //Act
        SensorTypeDTO actual = addSensorTypeController.addAndSaveSensorType(sensorTypeDataDTO);

        //Assert
        assertEquals(actual.sensorTypeID, sensorTypeDescription);
    }

    @Test
    void shouldThrowExceptionWhenSensorTypeAlreadyExists() throws InstantiationException {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

        LoadModelsAndUnit loadModelsAndUnit = new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);

        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);

        String supportedUnit = "Celsius";
        String sensorTypeDescription = "Temperature";
        SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO(sensorTypeDescription, supportedUnit);
        addSensorTypeController.addAndSaveSensorType(sensorTypeDataDTO);

        String expectedMessage = "Invalid sensor type data.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> addSensorTypeController.addAndSaveSensorType(sensorTypeDataDTO));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenAddingSensorTypeOfUnsupportedUnit() throws InstantiationException {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
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
    @Test
    void shouldThrowExceptionWhenAddingSensorWithSameDescriptionButDifferentUnit() throws InstantiationException {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

        LoadModelsAndUnit loadModelsAndUnit = new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);

        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);

        String sensorTypeDescription = "Temperature";

        String supportedUnit = "Celsius";
        String anotherSupportedUnit = "Percent";
        SensorTypeDataDTO sensorTypeDataDTO = new SensorTypeDataDTO(sensorTypeDescription, supportedUnit);
        addSensorTypeController.addAndSaveSensorType(sensorTypeDataDTO);


        SensorTypeDataDTO sensorTypeDataWithSameDescriptionDifferentUnit = new SensorTypeDataDTO(sensorTypeDescription, anotherSupportedUnit);
        String expectedMessage = "Invalid sensor type data.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> addSensorTypeController.addAndSaveSensorType(sensorTypeDataWithSameDescriptionDifferentUnit));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


}