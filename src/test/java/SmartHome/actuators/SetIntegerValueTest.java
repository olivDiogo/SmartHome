package SmartHome.actuators;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;

public class SetIntegerValueTest {

    /**
     * Should create instance of SetIntegerActuatorValue class.
     */
    @Test
    void shouldCreateInstanceOfSetIntegerActuatorValue() {
        // Arrange
        int value = 4;

        // Act
        SetIntegerValue setIntegerValue = new SetIntegerValue(value);

        // Assert
        assertNotNull(setIntegerValue);
    }

    /**
     * Should clone setInteger actuator value.
     */
    @Test
    void shouldCloneSwitchActuatorValue() {
        // arrange
        int value = 10;

        SetIntegerValue setIntegerValue = new SetIntegerValue(value);

        // act
        SetIntegerValue clonedSetIntegerValue = setIntegerValue.clone();

        // assert
        assertNotEquals(setIntegerValue, clonedSetIntegerValue);
    }


    @Test
    void shouldConvertToString(){
        // Arrange
        int value = 10;

        SetIntegerValue setIntegerValue = new SetIntegerValue(value);

        // Act
        String result = setIntegerValue.toString();

        // Assert
        assertEquals("10", result);
    }
}
