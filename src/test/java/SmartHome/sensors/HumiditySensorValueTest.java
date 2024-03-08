package SmartHome.sensors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link HumiditySensorValue}.
 * This class tests the functionality and validation within the HumiditySensorValue class.
 */
class HumiditySensorValueTest {

    /**
     * Tests that the HumiditySensorValue constructor accepts a value at the upper boundary (100).
     */
    @Test
    void shouldReturnNewHumiditySensorValue_WhenConstructorIsCalledWithValueAtUpperBoundary(){
        //Arrange
        double nValue = 100;

        //Act
        new HumiditySensorValue(nValue);
    }

    /**
     * Tests that the HumiditySensorValue constructor accepts a value at the lower boundary (0).
     */
    @Test
    void shouldReturnNewHumiditySensorValue_WhenConstructorIsCalledWithValueAtLowerBoundary(){
        //Arrange
        double nValue = 0;

        //Act
        new HumiditySensorValue(nValue);
    }

    /**
     * Tests that the HumiditySensorValue constructor throws an IllegalArgumentException
     * when the value is below the lower boundary (0).
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithValueBelowLowerBoundary() {
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensorValue(-1));

        //Assert
        assertEquals("Humidity value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Tests that the HumiditySensorValue constructor throws an IllegalArgumentException
     * when the value is above the upper boundary (100).
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithValueAboveUpperBoundary() {
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensorValue(101));

        //Assert
        assertEquals("Humidity value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Tests that the toString method of HumiditySensorValue correctly converts the value to a String.
     */
    @Test
    void shouldReturnStringValueOfHumidity_WhenToStringIsCalled() {
        //Arrange
        double dValue = 15.5;
        HumiditySensorValue humiditySensorValue = new HumiditySensorValue(dValue);

        //Act
        String actualString = humiditySensorValue.toString();

        //Assert
        assertEquals("15.5", actualString);
    }
}
