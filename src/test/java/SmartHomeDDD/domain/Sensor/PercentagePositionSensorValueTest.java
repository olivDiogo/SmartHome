package SmartHomeDDD.domain.Sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentagePositionSensorValueTest {

    /**
     * Tests the instantiation of PercentagePosicionSensorValue when the constructor arguments are valid.
     * The percentage is 0.
     */
    @Test
    void shouldInstantiatePercentagePosicionSensorValue_whenConstructorArgumentsAreValidAndEquals0() {
        // Arrange
        int percentage = 0;

        // Act
        new PercentagePositionSensorValue(percentage);
    }

    /**
     * Tests the instantiation of PercentagePosicionSensorValue when the constructor arguments are valid.
     * The percentage is 100.
     */
    @Test
    void shouldInstantiatePercentagePosicionSensorValue_whenConstructorArgumentsAreValidAndEquals100() {
        // Arrange
        int percentage = 100;

        // Act
        new PercentagePositionSensorValue(percentage);
    }

    /**
     * Tests the toString method of PercentagePosicionSensorValue when the percentage is 0.
     */
    @Test
    void shouldReturn0Percent_whenPercentageIs0() {
        // Arrange
        int percentage = 0;
        PercentagePositionSensorValue percentagePosicionSensorValue = new PercentagePositionSensorValue(percentage);

        // Act
        String result = percentagePosicionSensorValue.toString();

        // Assert
        assertEquals("0", result);
    }

    /**
     * Tests the toString method of PercentagePosicionSensorValue when the percentage is 100.
     */
    @Test
    void shouldReturn100Percent_whenPercentageIs100() {
        // Arrange
        int percentage = 100;
        PercentagePositionSensorValue percentagePosicionSensorValue = new PercentagePositionSensorValue(percentage);

        // Act
        String result = percentagePosicionSensorValue.toString();

        // Assert
        assertEquals("100", result);
    }

}