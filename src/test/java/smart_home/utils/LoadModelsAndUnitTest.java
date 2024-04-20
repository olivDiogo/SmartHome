package smart_home.utils;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator_model.ActuatorModelFactoryImpl;
import smart_home.domain.actuator_model.IActuatorModelFactory;
import smart_home.domain.sensor_model.ISensorModelFactory;
import smart_home.domain.sensor_model.SensorModelFactoryImpl;
import smart_home.domain.unit.IUnitFactory;
import smart_home.domain.unit.UnitFactoryImpl;
import smart_home.persistence.mem.ActuatorModelRepository;
import smart_home.persistence.mem.SensorModelRepository;
import smart_home.persistence.mem.UnitRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoadModelsAndUnitTest {
    @Test
    void shouldThrowIllegalArgumentException_WhenSensorModelRepositoryIsNull() {
        //Arrange
        SensorModelRepository sensorModelRepository = null;
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
        IUnitFactory unitFactory = mock(IUnitFactory.class);
        String expectedMessage = "Please enter a valid sensor model repository.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorModelRepositoryIsNull() {
        //Arrange
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        ActuatorModelRepository actuatorModelRepository = null;
        UnitRepository unitRepository = mock(UnitRepository.class);
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
        IUnitFactory unitFactory = mock(IUnitFactory.class);
        String expectedMessage = "Please enter a valid actuator model repository.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenUnitRepositoryIsNull() {
        //Arrange
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);
        UnitRepository unitRepository = null;
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
        IUnitFactory unitFactory = mock(IUnitFactory.class);
        String expectedMessage = "Please enter a valid unit repository.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenSensorModelFactoryIsNull() {
        //Arrange
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        ISensorModelFactory sensorModelFactory = null;
        IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
        IUnitFactory unitFactory = mock(IUnitFactory.class);
        String expectedMessage = "Please enter a valid sensor model factory.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorModelFactoryIsNull() {
        //Arrange
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IActuatorModelFactory actuatorModelFactory = null;
        IUnitFactory unitFactory = mock(IUnitFactory.class);
        String expectedMessage = "Please enter a valid actuator model factory.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenUnitFactoryIsNull() {
        //Arrange
        SensorModelRepository sensorModelRepository = mock(SensorModelRepository.class);
        ActuatorModelRepository actuatorModelRepository = mock(ActuatorModelRepository.class);
        UnitRepository unitRepository = mock(UnitRepository.class);
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
        IUnitFactory unitFactory = null;
        String expectedMessage = "Please enter a valid unit factory.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Integration test
     * verify is SensorModelRepository has any data
     */
    @Test
    void shouldLoadDefaultSensorModels_WhenLoadModelsAndUnitInstantiated() throws InstantiationException {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        UnitRepository unitRepository = new UnitRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();
        IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();
        IUnitFactory unitFactory = new UnitFactoryImpl();
        //Act
        LoadModelsAndUnit loadModelsAndUnit = new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);
        //Assert
        assertFalse(sensorModelRepository.findAll().isEmpty());
    }

    /**
     * Integration test
     * verify is ActuatorModelRepository has any data
     */
    @Test
    void shouldLoadDefaultActuatorModels_WhenLoadModelsAndUnitInstantiated() throws InstantiationException {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        UnitRepository unitRepository = new UnitRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();
        IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();
        IUnitFactory unitFactory = new UnitFactoryImpl();
        //Act
        LoadModelsAndUnit loadModelsAndUnit = new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);
        //Assert
        assertFalse(actuatorModelRepository.findAll().isEmpty());
    }

    /**
     * Integration test
     * verify is UnitRepository has any data
     */
    @Test
    void shouldLoadDefaultUnits_WhenLoadModelsAndUnitInstantiated() throws InstantiationException {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
        UnitRepository unitRepository = new UnitRepository();
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();
        IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();
        IUnitFactory unitFactory = new UnitFactoryImpl();
        //Act
        LoadModelsAndUnit loadModelsAndUnit = new LoadModelsAndUnit(sensorModelRepository, actuatorModelRepository, unitRepository, sensorModelFactory, actuatorModelFactory, unitFactory);
        //Assert
        assertFalse(unitRepository.findAll().isEmpty());
    }
}
