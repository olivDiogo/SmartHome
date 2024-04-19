package smart_home.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueSimulatorTest {
    @Test
    void generateRandomValueUsingDoubleWithValidBonds() {
        //Arrange
        double lowerBond = 0.0;
        double upperBond = 1.0;
        //Act
        double randomValue = ValueSimulator.generateRandomValue(lowerBond, upperBond);
        //Assert
        assertTrue(randomValue >= lowerBond && randomValue <= upperBond);
    }
    @Test
    void generateRandomValueWhenBondsAreInverted() {
        //Arrange
        double lowerBond = 1.0;
        double upperBond = 0.0;
        String expectedMessage = "Lower bond should be less than upper bond";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ValueSimulator.generateRandomValue(lowerBond, upperBond));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void generateRandomValueUsingIntWhenBondsAreInverted() {
        //Arrange
        int lowerBond = 1;
        int upperBond = 0;
        String expectedMessage = "Lower bond should be less than upper bond";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ValueSimulator.generateRandomValue(lowerBond, upperBond));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
     @Test
    void generateRandomValueUsingIntWithValidBonds() {
         int lowerBond = 0;
         int upperBond = 1;
         int randomValue = ValueSimulator.generateRandomValue(lowerBond, upperBond);
         assertTrue(randomValue >= lowerBond && randomValue <= upperBond);
     }
    @Test
    void generateRandomBoolean() {
        boolean randomValue = ValueSimulator.generateRandomValue();
        assertTrue(randomValue || randomValue == false);
    }

}