package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;


public class ImpActuatorFactoryTest {

    /**
     * Test of createActuator method, of class ImpActuatorFactory when the constructor has 4 parameters
     *
     */

    @Test
    public void shouldCreateAValidActuator_whenTheConstructorHas4Parameters() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.SwitchActuator");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");
        ActuatorName actuatorName = new ActuatorName("SwitchActuator");

        ImpActuatorFactory impActuatorFactory = new ImpActuatorFactory();

        //Act
        impActuatorFactory.createActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
    }

    /**
     *
     * Test of createActuator method, of class ImpActuatorFactory when the constructor has more than 4 parameters
     */

    @Test
   public void shouldCreateAValidActuator_whenTheConstructorHasMoreThan4Parameters() {
       //Arrange
       DeviceID deviceID = new DeviceID("1");
       ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.SetIntegerActuator");
       ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");
       ActuatorName actuatorName = new ActuatorName("SetIntegerActuator");
       SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(1, 10);

       ImpActuatorFactory impActuatorFactory = new ImpActuatorFactory();

       //Act
       impActuatorFactory.createActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
   }
}
