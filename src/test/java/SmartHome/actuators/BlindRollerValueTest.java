package SmartHome.actuators;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class BlindRollerValueTest {

    @Test
    void seeIfConstructorWorks() {
        //Arrange
        int nValue = 50;

        //Act
        BlindRollerValue blindRollerValue = new BlindRollerValue(nValue);

        //Assert
        assertNotNull(blindRollerValue);
    }

    @Test
    void cloneCantBeEqualsToOriginal() {
        // Arrange
        int nValue = 50;
        BlindRollerValue blindRollerValue = new BlindRollerValue(nValue);

        // Act
        BlindRollerValue clonedValue = blindRollerValue.clone();

        // Assert
        assertNotEquals(blindRollerValue, clonedValue);
    }

    @Test
    void cloneReturnsTheSameValueAsOriginal() {
        // Arrange
        int nValue = 50;
        BlindRollerValue blindRollerValue = new BlindRollerValue(nValue);

        // Act
        BlindRollerValue clonedValue = blindRollerValue.clone();

        // Assert
        assertEquals(blindRollerValue.get_nValue(), clonedValue.get_nValue());
    }

    @Test
    void catchCloneNotSupportedException() {
        // Arrange
        int nValue = 50;
        BlindRollerValue blindRollerValue = new BlindRollerValue(nValue);
        BlindRollerValue clonedValue = blindRollerValue.clone();

        // Act
        clonedValue = clonedValue.clone();

        // Assert
        assertEquals(blindRollerValue.get_nValue(), clonedValue.get_nValue());
    }
}


