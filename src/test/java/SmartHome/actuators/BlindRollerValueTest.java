package SmartHome.actuators;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class BlindRollerValueTest {

    @Test
    void seeIfConstructorWorks() {
        //Arrange
        int nValue = 50;

        //Act
        new BlindRollerValue(nValue);
    }

    @Test
    void cloneReturnsTheSameValueAsOriginal() {
        // Arrange
        String nValue = "50";
        BlindRollerValue blindRollerValue = new BlindRollerValue(Integer.parseInt(nValue));
        // Act
        BlindRollerValue result = blindRollerValue.clone();
        // Assert
        assertEquals(blindRollerValue.toString(), result.toString());
    }

}


