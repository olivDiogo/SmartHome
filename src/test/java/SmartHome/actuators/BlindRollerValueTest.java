package SmartHome.actuators;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BlindRollerValueTest {


    /**
     * Test to see if the constructor works.
     */
    @Test
    void shouldInstantiateBlindRollerValueWhenValidValueIsProvided() {
        //Arrange
        int nValue = 50;

        //Act
        new BlindRollerValue(nValue);
    }

    /**
     * Test to see if the constructor throws an exception when the value is above 100.
     */
    @Test
    void shouldThrowExceptionWhenValueAbove100IsProvided() {
        // Arrange
        int nValue = 101;

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new BlindRollerValue(nValue);
        });
        assertEquals("The value must be between 0 and 100.", thrown.getMessage());
    }

    /**
     * Test to see if the constructor throws an exception when the value is below 0.
     */
    @Test
    void shouldThrowExceptionWhenValueBelow0IsProvided() {
        // Arrange
        int nValue = -1;

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new BlindRollerValue(nValue);
        });
        assertEquals("The value must be between 0 and 100.", thrown.getMessage());
    }

    /**
     * Test to see if clone works
     */
    @Test
    void shouldCloneSuccessfullyWhenCloneMethodIsCalled() {
        // Arrange
        int nValue = 50;
        BlindRollerValue blindRollerValue = new BlindRollerValue(nValue);

        // Act
        BlindRollerValue result = blindRollerValue.clone();

        // Assert
        assertEquals(blindRollerValue.toString(), result.toString());
    }

    /**
     * Test for low boundary value
     */
    @Test
    void shouldCloneSuccessfullyWhenLowBoundaryValueIsProvided() {
        // Arrange
        int nValue = 0;
        BlindRollerValue blindRollerValue = new BlindRollerValue(nValue);
        // Act
        BlindRollerValue result = blindRollerValue.clone();

        // Assert
        assertEquals(blindRollerValue.toString(), result.toString());
    }
    /**
     * Test for high boundary value
     */
    @Test
    void shouldCloneSuccessfullyWhenHighBoundaryValueIsProvided() {
        // Arrange
        int nValue = 100;
        BlindRollerValue blindRollerValue = new BlindRollerValue(nValue);
        // Act
        BlindRollerValue result = blindRollerValue.clone();

        // Assert
        assertEquals(blindRollerValue.toString(), result.toString());
    }
    /**
     * Test method toString
     */
    @Test
    void shouldReturnCorrectStringWhenToStringIsCalled() {
        // Arrange
        int nValue = 50;
        BlindRollerValue blindRollerValue = new BlindRollerValue(nValue);
        // Act
        String result = blindRollerValue.toString();

        // Assert
        assertEquals("50", result);
    }

}


