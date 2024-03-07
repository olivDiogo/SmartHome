package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricConsumptionWhValueTest {
    @Test
    void shouldInstantiateElectricConsumptionWhValue() {
        //Arrange
        int value = 0;
        //Act
        new ElectricConsumptionWhValue(value);
    }
    @Test
    void shouldThrowExceptionWhenValueIsNegative() {
        //Arrange
        int value = -1;
        String expectedMessage = "Consumption cannot be negative.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ElectricConsumptionWhValue(value));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldReturnElectricConsumptionWh() {
        //Arrange
        int value = 5;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        String expected = "ElectricConsumptionWh{5}";
        //Act
        String actual = electricConsumptionWhValue.toString();
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void cloneInstanceShouldReturnSameInformation() {
        //Arrange
        int value = 5;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        //Act
        ElectricConsumptionWhValue clonedElectricConsumptionWhValue = electricConsumptionWhValue;
        //Assert
        assertEquals(electricConsumptionWhValue.toString(), clonedElectricConsumptionWhValue.toString());
    }


}