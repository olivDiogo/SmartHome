package SmartHomeDDD.controller;
import SmartHomeDDD.DTO.SensorTypeDTO;
import SmartHomeDDD.DTO.SensorTypeDataDTO;
import SmartHomeDDD.DTO.UnitDTO;
import SmartHomeDDD.assembler.SensorTypeAssembler;
import SmartHomeDDD.assembler.UnitAssembler;
import SmartHomeDDD.domain.SensorModel.ImpSensorModelFactory;
import SmartHomeDDD.domain.SensorModel.SensorModelFactory;
import SmartHomeDDD.domain.SensorType.ImpSensorTypeFactory;
import SmartHomeDDD.domain.SensorType.SensorType;
import SmartHomeDDD.domain.Unit.ImpUnitFactory;
import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.domain.Unit.UnitFactory;
import SmartHomeDDD.repository.SensorModelRepository;
import SmartHomeDDD.repository.UnitRepository;
import SmartHomeDDD.repository.SensorTypeRepository;
import SmartHomeDDD.service.ConfigurationService;
import SmartHomeDDD.service.SensorTypeService;
import SmartHomeDDD.service.UnitService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddSensorTypeControllerTest {
    @Test
    void shouldThrowExceptionWhenSensorTypeServiceIsNull() {
        //Arrange
        SensorTypeService sensorTypeService = null;
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
        UnitRepository unitRepository = new UnitRepository();
        UnitFactory unitFactory = new ImpUnitFactory();
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
        ImpSensorTypeFactory sensorTypeFactory = new ImpSensorTypeFactory();
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
        ImpSensorTypeFactory sensorTypeFactory = new ImpSensorTypeFactory();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);        SensorTypeAssembler sensorTypeAssembler = null;
        UnitFactory unitFactory = new ImpUnitFactory();
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
        ImpSensorTypeFactory sensorTypeFactory = new ImpSensorTypeFactory();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
        UnitFactory unitFactory = new ImpUnitFactory();
        UnitService unitService = new UnitService(unitRepository, unitFactory);
        UnitAssembler unitAssembler = null;
        String expectedMessage = "Valid UnitAssembler is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldReturnEmptyListOfUnitsWhenNoUnitLoaded() throws InstantiationException {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        ImpSensorTypeFactory sensorTypeFactory = new ImpSensorTypeFactory();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        UnitFactory unitFactory = new ImpUnitFactory();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        int expected = 0;
        AddSensorTypeController addSensorTypeController = new AddSensorTypeController(sensorTypeService, sensorTypeAssembler, unitService, unitAssembler);
        //Act
        int actual = addSensorTypeController.getSupportedUnits().size();
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void shouldReturnListOfUnitsWhenUnitsLoaded() throws InstantiationException {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        ImpSensorTypeFactory sensorTypeFactory = new ImpSensorTypeFactory();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        UnitFactory unitFactory = new ImpUnitFactory();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModelFactory sensorModelFactory = new ImpSensorModelFactory();

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
        ImpSensorTypeFactory sensorTypeFactory = new ImpSensorTypeFactory();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        UnitFactory unitFactory = new ImpUnitFactory();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModelFactory sensorModelFactory = new ImpSensorModelFactory();

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
        ImpSensorTypeFactory sensorTypeFactory = new ImpSensorTypeFactory();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        UnitFactory unitFactory = new ImpUnitFactory();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModelFactory sensorModelFactory = new ImpSensorModelFactory();

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
        ImpSensorTypeFactory sensorTypeFactory = new ImpSensorTypeFactory();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        UnitFactory unitFactory = new ImpUnitFactory();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModelFactory sensorModelFactory = new ImpSensorModelFactory();

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
        ImpSensorTypeFactory sensorTypeFactory = new ImpSensorTypeFactory();
        UnitRepository unitRepository = new UnitRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

        UnitFactory unitFactory = new ImpUnitFactory();
        UnitService unitService = new UnitService(unitRepository, unitFactory);

        UnitAssembler unitAssembler = new UnitAssembler();
        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModelFactory sensorModelFactory = new ImpSensorModelFactory();

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