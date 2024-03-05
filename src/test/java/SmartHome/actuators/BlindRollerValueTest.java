package SmartHome.actuators;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BlindRollerValueTest {


    /**
     * Test to see if the constructor works.
     */
    @Test
    void seeIfConstructorWorks() {
        //Arrange
        int nValue = 50;

        //Act
        new BlindRollerValue(nValue);
    }

    /**
     * Test to see if the constructor throws an exception when the value is above 100.
     */
    @Test
    void valueAbove100ShouldThrowException() {
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
    void valueBelow0ShouldThrowException() {
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
    void seeIfCloneWorks() {
        // Arrange
        int nValue = 50;
        BlindRollerValue blindRollerValue = new BlindRollerValue(nValue);

        // Act
        BlindRollerValue result = blindRollerValue.clone();

        // Assert
        assertEquals(blindRollerValue.toString(), result.toString());
    }

}


