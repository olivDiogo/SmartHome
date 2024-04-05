package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerActuator;
import SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerActuatorLimits;
import SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuator;
import SmartHomeDDD.valueObject.ActuatorName;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.DeviceID;
import SmartHomeDDD.valueObject.ModelPath;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ImpActuatorFactoryTest {

    /**
     * Test of createActuator method, of class ImpActuatorFactory when the constructor has 4 parameters
     */

    @Test
    void shouldCreateAValidActuator_whenTheConstructorHas4Parameters() {
        //Arrange
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuator");

        ActuatorTypeID actuatorTypeIdMock = mock(ActuatorTypeID.class);
        when(actuatorTypeIdMock.getId()).thenReturn("Switch");

        ActuatorName actuatorNameMock = mock(ActuatorName.class);
        ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

        // Act
        SwitchActuator actuator = (SwitchActuator) impActuatorFactory.createActuator(deviceIdMock, modelPathMock, actuatorTypeIdMock, actuatorNameMock);

        // Assert
        assertNotNull(actuator);

    }

    /**
     * Test of createActuator method, of class ImpActuatorFactory when the constructor has more than 4 parameters
     */

    @Test
    void shouldCreateAValidActuator_whenTheConstructorHasMoreThan4Parameters() {
        //Arrange
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerActuator");

        ActuatorTypeID actuatorTypeIDMock = mock(ActuatorTypeID.class);
        when(actuatorTypeIDMock.getId()).thenReturn("SetInteger");
        ActuatorName actuatorNameMock = mock(ActuatorName.class);
        SetIntegerActuatorLimits setIntegerActuatorLimitsMock = mock(SetIntegerActuatorLimits.class);


        ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

        // Act
        SetIntegerActuator actuator = (SetIntegerActuator) impActuatorFactory.createActuator(deviceIdMock, modelPathMock, actuatorTypeIDMock, actuatorNameMock, setIntegerActuatorLimitsMock);

        // Assert
        assertNotNull(actuator);
    }

    /**
     * Test for providing a wrong model path, which should return null.
     */
    @Test
    void shouldBeNull_whenModelPathIsWrong() {
        //Arrange
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("SmartHomeDDD.domain.Sensor.WrongSensor");

        ActuatorTypeID actuatorTypeIDMock = mock(ActuatorTypeID.class);
        ActuatorName actuatorNameMock = mock(ActuatorName.class);

        ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

        // Act + Assert
        IActuator result = impActuatorFactory.createActuator(deviceIdMock, modelPathMock, actuatorTypeIDMock, actuatorNameMock);

        // Assert
        assertNull(result);
    }


    /**
     * Test of createActuator method, of class ImpActuatorFactory when the constructor has 4 parameters and the wrong object type is passed
     */
    @Test
    void shouldReturnNull_whenParameterTypesAreNotAssignableFromParameters() {
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerActuator");

        ActuatorTypeID actuatorTypeIdMock = mock(ActuatorTypeID.class);
        ActuatorName actuatorNameMock = mock(ActuatorName.class);
        String wrongObject = "wrong object";

        ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

        // Act
        IActuator result = impActuatorFactory.createActuator(deviceIdMock, modelPathMock, actuatorTypeIdMock, actuatorNameMock, wrongObject);

        // Assert
        assertNull(result);
    }

    /**
     * Test for providing less than 4 constructor parameters, which should return null.
     */
    @Test
    public void shouldThrowException_whenWrongNumberOfConstructorParameters() {
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuator");

        ActuatorName actuatorNameMock = mock(ActuatorName.class);

        ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

        String expectedMessage = "At least 4 parameters are required.";

        // Act + Assert
        Exception e = Assert.assertThrows(IllegalArgumentException.class, () -> {
            impActuatorFactory.createActuator(deviceIdMock, modelPathMock, actuatorNameMock);
        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}


