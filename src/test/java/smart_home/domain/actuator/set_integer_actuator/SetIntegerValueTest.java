package smart_home.domain.actuator.set_integer_actuator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SetIntegerValueTest {
    /**
     * Test for SetIntegerValue instantiation
     */
    @Test
    void shouldInstantiateSetIntegerValue() {
        // Arrange
        int value = 1;

        // Act
        SetIntegerValue result = new SetIntegerValue(value);

        // Assert
        assertNotNull(result);
    }

    /**
     * Test for method toString
     */
    @Test
    void shouldReturnStringValue() {
        // Arrange
        int value = 1;
        SetIntegerValue setIntegerValue = new SetIntegerValue(value);

        String expected = "1";

        // Act
        String result = setIntegerValue.toString();

        // Assert
        assertEquals(result,expected);
    }

    /**
     * Test method equals when the instance is compared to itself.
     */
    @Test
    void shouldReturnTrue_whenComparedToItself() {
        // Arrange
        int value = 1;
        SetIntegerValue setIntegerValue = new SetIntegerValue(value);

        // Act
        boolean result = setIntegerValue.equals(setIntegerValue);

        // Assert
        assertEquals(result,true);
    }

    /**
     * Test of method equals when the instances are not equal.
     */
    @Test
    void shouldReturnFalse_whenInstancesAreNotEqual() {
        // Arrange
        int value1 = 1;
        int value2 = 2;
        SetIntegerValue setIntegerValue1 = new SetIntegerValue(value1);
        SetIntegerValue setIntegerValue2 = new SetIntegerValue(value2);

        // Act
        boolean result = setIntegerValue1.equals(setIntegerValue2);

        // Assert
        assertEquals(result,false);
    }

    /**
     * Test of method equals when the instance is compared to an object of a different class.
     */
    @Test
    void shouldReturnFalse_whenComparedWithDifferentClass() {
        // Arrange
        int value = 1;
        SetIntegerValue setIntegerValue = new SetIntegerValue(value);

        // Act
        boolean result = setIntegerValue.equals(new Object());

        // Assert
        assertEquals(result,false);
    }
}
