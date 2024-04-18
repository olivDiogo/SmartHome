package smart_home.persistence.jpa.data_model;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator.IActuator;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorDataModelTest {

    /**
     * Test for the empty constructor of the {@link ActuatorDataModel} class.
     */
    @Test
    void shouldInstantiateActuatorDataModel_whenEmptyConstructorIsCalled() {
        // Arrange
        ActuatorDataModel actuatorDataModel = new ActuatorDataModel();

        // Act
        assertNotNull(actuatorDataModel);
    }

    /**
     * Test for the constructor of the {@link ActuatorDataModel} class.
     */
    @Test
    void shouldInstantiateActuatorDataModel_WhenAttributesAreValid() {
        //Arrange
        ActuatorID actuatorIDDouble = mock(ActuatorID.class);
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);

        IActuator actuatorDouble = mock(IActuator.class);

        when(actuatorIDDouble.getID()).thenReturn("123");
        when(deviceID.getID()).thenReturn("123");
        when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
        when(actuatorTypeID.getID()).thenReturn("Switch");
        when(actuatorName.getActuatorName()).thenReturn("SwitchActuator");

        when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
        when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
        when(actuatorDouble.getModelPath()).thenReturn(modelPath);
        when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
        when(actuatorDouble.getName()).thenReturn(actuatorName);

        //Act
        ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

        //Assert
        assertNotNull(actuatorDataModel);

    }

    /**
     * Test for the constructor of the {@link ActuatorDataModel} class when actuator is null.
     * */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorDataModelIsNull() {
        //Arrange
        IActuator actuatorDouble = null;

        String expectedMessage = "Actuator cannot be null";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorDataModel(actuatorDouble));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test for the getActuatorID method of the {@link ActuatorDataModel} class.
     */
    @Test
    void shouldReturnActuatorID_WhenGetActuatorIDIsCalled() {
        //Arrange
        ActuatorID actuatorIDDouble = mock(ActuatorID.class);
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);

        IActuator actuatorDouble = mock(IActuator.class);

        when(actuatorIDDouble.getID()).thenReturn("123");
        when(deviceID.getID()).thenReturn("device1");
        when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
        when(actuatorTypeID.getID()).thenReturn("Switch");
        when(actuatorName.getActuatorName()).thenReturn("Switch");

        when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
        when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
        when(actuatorDouble.getModelPath()).thenReturn(modelPath);
        when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
        when(actuatorDouble.getName()).thenReturn(actuatorName);

        ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

        String expected = actuatorDataModel.getActuatorID();

        //Act
        String result = actuatorDataModel.getActuatorID();

        //Assert
        assertEquals(expected, result);

    }

    /**
     * Test for the getDeviceID method of the {@link ActuatorDataModel} class.
     */
    @Test
    void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
        //Arrange
        ActuatorID actuatorIDDouble = mock(ActuatorID.class);
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);

        IActuator actuatorDouble = mock(IActuator.class);

        when(actuatorIDDouble.getID()).thenReturn("123");
        when(deviceID.getID()).thenReturn("device1");
        when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
        when(actuatorTypeID.getID()).thenReturn("Switch");
        when(actuatorName.getActuatorName()).thenReturn("Switch");

        when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
        when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
        when(actuatorDouble.getModelPath()).thenReturn(modelPath);
        when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
        when(actuatorDouble.getName()).thenReturn(actuatorName);

        ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

        String expected = actuatorDataModel.getDeviceID();

        //Act
        String result = actuatorDataModel.getDeviceID();

        //Assert
        assertEquals(expected, result);

    }

    /**
     * Test for the getModelPath method of the {@link ActuatorDataModel} class.
     */
    @Test
    void shouldReturnModelPath_WhenGetModelPathIsCalled() {
        //Arrange
        ActuatorID actuatorIDDouble = mock(ActuatorID.class);
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);

        IActuator actuatorDouble = mock(IActuator.class);

        when(actuatorIDDouble.getID()).thenReturn("123");
        when(deviceID.getID()).thenReturn("device1");
        when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
        when(actuatorTypeID.getID()).thenReturn("Switch");
        when(actuatorName.getActuatorName()).thenReturn("Switch");

        when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
        when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
        when(actuatorDouble.getModelPath()).thenReturn(modelPath);
        when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
        when(actuatorDouble.getName()).thenReturn(actuatorName);

        ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

        String expected = actuatorDataModel.getModelPath();

        //Act
        String result = actuatorDataModel.getModelPath();

        //Assert
        assertEquals(expected, result);

    }


    /**
     * Test for the getActuatorTypeID method of the {@link ActuatorDataModel} class.
     */
    @Test
    void shouldReturnActuatorTypeID_WhenGetActuatorTypeIDIsCalled() {
        //Arrange
        ActuatorID actuatorIDDouble = mock(ActuatorID.class);
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);

        IActuator actuatorDouble = mock(IActuator.class);

        when(actuatorIDDouble.getID()).thenReturn("123");
        when(deviceID.getID()).thenReturn("device1");
        when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
        when(actuatorTypeID.getID()).thenReturn("Switch");
        when(actuatorName.getActuatorName()).thenReturn("Switch");

        when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
        when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
        when(actuatorDouble.getModelPath()).thenReturn(modelPath);
        when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
        when(actuatorDouble.getName()).thenReturn(actuatorName);

        ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

        String expected = actuatorDataModel.getActuatorTypeID();

        //Act
        String result = actuatorDataModel.getActuatorTypeID();

        //Assert
        assertEquals(expected, result);

    }


    /**
     * Test for the getActuatorName method of the {@link ActuatorDataModel} class.
     */
    @Test
    void shouldReturnActuatorName_WhenGetActuatorNameIsCalled() {
        //Arrange
        ActuatorID actuatorIDDouble = mock(ActuatorID.class);
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);

        IActuator actuatorDouble = mock(IActuator.class);

        when(actuatorIDDouble.getID()).thenReturn("123");
        when(deviceID.getID()).thenReturn("device1");
        when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
        when(actuatorTypeID.getID()).thenReturn("Switch");
        when(actuatorName.getActuatorName()).thenReturn("Switch");

        when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
        when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
        when(actuatorDouble.getModelPath()).thenReturn(modelPath);
        when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
        when(actuatorDouble.getName()).thenReturn(actuatorName);

        ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

        String expected = actuatorDataModel.getActuatorName();

        //Act
        String result = actuatorDataModel.getActuatorName();

        //Assert
        assertEquals(expected, result);

    }

}
