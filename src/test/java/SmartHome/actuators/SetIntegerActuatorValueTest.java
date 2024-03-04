package SmartHome.actuators;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SetIntegerActuatorValueTest {

    /**
     * Should create instance of SetIntegerActuatorValue class.
     */
    @Test
    void shouldCreateInstanceOfSetIntegerActuatorValue() {
        // Arrange
        int value = 4;

        // Act
        SetIntegerActuatorValue setIntegerActuatorValue = new SetIntegerActuatorValue(value);

        // Assert
        assertNotNull(setIntegerActuatorValue);
    }

    /**
     * Should clone setInteger actuator value.
     */
    @Test
    void shouldCloneSwitchActuatorValue() {
        // arrange
        int value = 10;

        SetIntegerActuatorValue setIntegerActuatorValue = new SetIntegerActuatorValue(value);

        // act
        SetIntegerActuatorValue clonedSetIntegerActuatorValue = setIntegerActuatorValue.clone();

        // assert
        assertNotEquals(setIntegerActuatorValue, clonedSetIntegerActuatorValue);
    }


    @Test
    void shouldConvertToString(){
        // Arrange
        int value = 10;

        SetIntegerActuatorValue setIntegerActuatorValue = new SetIntegerActuatorValue(value);

        // Act
        String result = setIntegerActuatorValue.toString();

        // Assert
        assertEquals("10", result);
    }
}
