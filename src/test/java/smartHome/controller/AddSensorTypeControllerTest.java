package smartHome.controller;

import smartHome.dto.SensorTypeDTO;
import smartHome.dto.SensorTypeDataDTO;
import smartHome.dto.UnitDTO;
import smartHome.assembler.SensorTypeAssembler;
import smartHome.assembler.UnitAssembler;
import smartHome.domain.sensorModel.SensorModelFactoryImpl;
import smartHome.domain.sensorModel.ISensorModelFactory;
import smartHome.domain.sensorType.SensorTypeFactoryImpl;
import smartHome.domain.unit.UnitFactoryImpl;
import smartHome.domain.unit.IUnitFactory;
import smartHome.persistence.mem.SensorModelRepository;
import smartHome.persistence.mem.SensorTypeRepository;
import smartHome.persistence.mem.UnitRepository;
import smartHome.service.ConfigurationService;
import smartHome.service.SensorTypeService;
import smartHome.service.UnitService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddSensorTypeControllerTest {
    @Test
    void shouldThrowExceptionWhenSensorTypeServiceIsNull() {
        //Arrange
        SensorTypeService sensorTypeService = null;
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
        UnitRepository unitRepository = new UnitRepository();
        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitService unitService = new UnitService(unitRepository, unitFactory);
        UnitAssembler unitAssembler = new UnitAssembler();
        String expectedMessage = "Valid SensorTypeService is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenUnitServiceIsNull() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
        UnitService unitService = null;
        UnitAssembler unitAssembler = new UnitAssembler();
        String expectedMessage = "Valid UnitService is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenSensorTypeAssemblerIsNull() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);        SensorTypeAssembler sensorTypeAssembler = null;
        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitService unitService = new UnitService(unitRepository, unitFactory);
        UnitAssembler unitAssembler = new UnitAssembler();
        String expectedMessage = "Valid SensorTypeAssembler is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenUnitAssemblerIsNull() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitService unitService = new UnitService(unitRepository, unitFactory);
        UnitAssembler unitAssembler = null;
        String expectedMessage = "Valid UnitAssembler is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturnListOfUnitsWhenUnitsLoaded() throws InstantiationException {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ConfigurationService configurationService = new ConfigurationService(sensorModelRepository, unitRepository,sensorModelFactory, unitFactory);

        List<UnitDTO> expected = unitAssembler.domainToDTO(unitRepository.findAll());

        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler);
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
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ConfigurationService configurationService = new ConfigurationService(sensorModelRepository, unitRepository,sensorModelFactory, unitFactory);

        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler);

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
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ConfigurationService configurationService = new ConfigurationService(sensorModelRepository, unitRepository,sensorModelFactory, unitFactory);

        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler);

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
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ConfigurationService configurationService = new ConfigurationService(sensorModelRepository, unitRepository,sensorModelFactory, unitFactory);

        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler);

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
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        IUnitFactory unitFactory = new UnitFactoryImpl();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

        ConfigurationService configurationService = new ConfigurationService(sensorModelRepository, unitRepository,sensorModelFactory, unitFactory);

        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler);

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