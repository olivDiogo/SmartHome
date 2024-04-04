package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerActuatorLimits;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ImpActuatorFactoryTest {

    /**
     * Test of createActuator method, of class ImpActuatorFactory when the constructor has 4 parameters
     */

    @Test
    void shouldCreateAValidActuator_whenTheConstructorHas4Parameters() {
        //Arrange
        DeviceID deviceIDMock = mock(DeviceID.class);

        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuator");

        ActuatorTypeID actuatorTypeIDMock = mock(ActuatorTypeID.class);
        when(actuatorTypeIDMock.getId()).thenReturn("SwitchActuator");

        ActuatorName actuatorNameMock = mock(ActuatorName.class);

        ImpActuatorFactory impActuatorFactory = new ImpActuatorFactory();

        //Act
        Actuator result = impActuatorFactory.createActuator(deviceIDMock, modelPathMock, actuatorTypeIDMock, actuatorNameMock);

        //Assert
        assertNotNull(result);
    }


    /**
     * Test of createActuator method, of class ImpActuatorFactory when the constructor has more than 4 parameters
     */

    @Test
    void shouldCreateAValidActuator_whenTheConstructorHasMoreThan4Parameters() {
        //Arrange
        DeviceID deviceIDMock = mock(DeviceID.class);

        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerActuator");

        ActuatorTypeID actuatorTypeIDMock = mock(ActuatorTypeID.class);
        when(actuatorTypeIDMock.getId()).thenReturn("SetInteger");

        ActuatorName actuatorNameMock = mock(ActuatorName.class);

        SetIntegerActuatorLimits limitsMock = mock(SetIntegerActuatorLimits.class);

        ImpActuatorFactory impActuatorFactory = new ImpActuatorFactory();

        //Act
        Actuator result = impActuatorFactory.createActuator(deviceIDMock, modelPathMock, actuatorTypeIDMock, actuatorNameMock, limitsMock);

        //Assert
        assertNotNull(result);
    }

    /**
     * Test of createActuator method, of class ImpActuatorFactory when the constructor has less than 4 parameters
     */
    @Test
    void shouldBeNull_whenTheConstructorHasLessThan4Parameters() {
        //Arrange
        DeviceID deviceIDMock = mock(DeviceID.class);

        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("SmartHomeDDD.domain.Actuator.SetIntegerActuator");

        ActuatorTypeID actuatorTypeIDMock = mock(ActuatorTypeID.class);

        ImpActuatorFactory impActuatorFactory = new ImpActuatorFactory();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> impActuatorFactory.createActuator(deviceIDMock, modelPathMock, actuatorTypeIDMock));
    }


    /**
     * Test of createActuator method, of class ImpActuator when the constructor has 4 parameters and the class does not exist
     */
    @Test
    void shouldBeNull_whenNoMatchingClass() {
        //Arrange
        DeviceID deviceIDMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("NonExistentClass");

        ActuatorTypeID actuatorTypeIDMock = mock(ActuatorTypeID.class);

        ActuatorName actuatorNameMock = mock(ActuatorName.class);

        ImpActuatorFactory impActuatorFactory = new ImpActuatorFactory();

        //Act
        Actuator result = impActuatorFactory.createActuator(deviceIDMock, modelPathMock, actuatorTypeIDMock, actuatorNameMock);

        //Assert
        assertNull(result);
    }

    /**
     * Test of createActuator method, of class ImpActuatorFactory when the constructor has 4 parameters and the wrong object type is passed
     */
    @Test
    void shouldBeNull_whenWrongObjectTypeInConstructorParameters() {
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("SmartHomeDDD.domain.Actuator.SetIntegerActuator");

        ActuatorTypeID actuatorTypeIdMock = mock(ActuatorTypeID.class);
        ActuatorName actuatorNameMock = mock(ActuatorName.class);
        String wrongObject = "wrong object";

        ImpActuatorFactory impActuatorFactory = new ImpActuatorFactory();

        // Act + Assert
        Actuator result = impActuatorFactory.createActuator(deviceIdMock, modelPathMock, actuatorTypeIdMock, actuatorNameMock, wrongObject);

        // Assert
        assertNull(result);
    }

}



