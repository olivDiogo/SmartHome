package smarthome.domain.sensor.humidity_sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumiditySensorValueTest {
    /**
     * Tests the instantiation of HumiditySensorValue when the constructor arguments are valid.
     * The value is at the upper limit.
     */
    @Test
    void shouldInstantiateHumiditySensorValue_whenConstructorArgumentsAreWithinUpperLimit() {
        // Arrange
        int value = 100;

        // Act
        HumiditySensorValue result = new HumiditySensorValue(value);

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the instantiation of HumiditySensorValue when the constructor arguments are invalid.
     * The value is above the upper limit.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenConstructorArgumentsAreAboveTheUpperLimit() {
        // Arrange
        int value = 101;
        String expectedMessage = "Humidity value must be between 0 and 100";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensorValue(value));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests the instantiation of HumiditySensorValue when the constructor arguments are invalid.
     * The value is below the lower limit.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenConstructorArgumentsAreBelowTheLowerLimit() {
        // Arrange
        int value = -1;
        String expectedMessage = "Humidity value must be between 0 and 100";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensorValue(value));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests the instantiation of HumiditySensorValue when the constructor arguments are valid.
     * The value is at the lower limit.
     */
    @Test
    void shouldInstantiateHumiditySensorValue_whenConstructorArgumentsAreWithinLowerLimit() {
        // Arrange
        int value = 0;

        // Act
        HumiditySensorValue result = new HumiditySensorValue(value);

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the toString method of HumiditySensorValue.
     */
    @Test
    void shouldReturnStringValue_whenToStringIsCalled() {
        // Arrange
        int value = 50;
        HumiditySensorValue humiditySensorValue = new HumiditySensorValue(value);

        // Act
        String result = humiditySensorValue.toString();

        // Assert
        assertEquals("50", result);
    }

    /**
     * Tests the equals method of HumiditySensorValue when the objects are equal.
     */
    @Test
    void shouldReturnTrue_whenEqualsIsCalledWithEqualObjects() {
        // Arrange
        int value = 50;
        HumiditySensorValue humiditySensorValue1 = new HumiditySensorValue(value);
        HumiditySensorValue humiditySensorValue2 = new HumiditySensorValue(value);

        // Act
        boolean result = humiditySensorValue1.equals(humiditySensorValue2);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests the equals method of HumiditySensorValue when the objects are not equal.
     */
    @Test
    void shouldReturnFalse_whenEqualsIsCalledWithDifferentObjects() {
        // Arrange
        int value1 = 50;
        int value2 = 60;
        HumiditySensorValue humiditySensorValue1 = new HumiditySensorValue(value1);
        HumiditySensorValue humiditySensorValue2 = new HumiditySensorValue(value2);

        // Act
        boolean result = humiditySensorValue1.equals(humiditySensorValue2);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests the equals method of HumiditySensorValue when the object is not an instance of HumiditySensorValue.
     */
@Test
void shouldReturnFalse_whenEqualsIsCalledWithDifferentObject() {
    // Arrange
    int value = 50;
    HumiditySensorValue humiditySensorValue = new HumiditySensorValue(value);
    Object object = new Object();

    // Act
    boolean result = humiditySensorValue.equals(object);

    // Assert
    assertFalse(result);
  }

    /**
     * Tests the hashCode method of HumiditySensorValue.
     */
    @Test
    void shouldReturnHashCodeOfHumiditySensorValue() {
        // Arrange
        int value = 50;
        HumiditySensorValue humiditySensorValue = new HumiditySensorValue(value);

        // Act
        int result = humiditySensorValue.hashCode();

        // Assert
        assertEquals(humiditySensorValue.hashCode(), result);
    }

    /**
     * Tests the equals method of HumiditySensorValue when the objects are equal.
     */
    @Test
    void shouldReturnEqualHashCodeForIdenticalObjects() {
        // Arrange
        int value = 50;
        HumiditySensorValue humiditySensorValue1 = new HumiditySensorValue(value);
        HumiditySensorValue humiditySensorValue2 = new HumiditySensorValue(value);
        boolean expected = humiditySensorValue1.equals(humiditySensorValue2);
        // Act
        boolean result = humiditySensorValue1.hashCode() == humiditySensorValue2.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnNotEqualsForHashCodeDifferentFromZero(){
        //Arrange
        int humidity = 0;
        int humidity1 = 10;

        HumiditySensorValue humiditySensorValue = new HumiditySensorValue(humidity);
        HumiditySensorValue humiditySensorValue2 = new HumiditySensorValue(humidity1);
        int expected = humiditySensorValue.hashCode();
        //Act
        int result = humiditySensorValue2.hashCode();

        //Assert
        assertNotEquals(expected,result);
    }
}
