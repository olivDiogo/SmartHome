package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricConsumptionWhValueTest {
    @Test
    void shouldInstantiateElectricConsumptionWhValue() {
        //Arrange
        double value = 0.0;
        //Act
        new ElectricConsumptionWhValue(value);
    }
    @Test
    void shouldThrowExceptionWhenValueIsNegative() {
        //Arrange
        double value = -1.0;
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
        double value = 5.0;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        String expected = "ElectricConsumptionWh{5.0}";
        //Act
        String actual = electricConsumptionWhValue.toString();
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void cloneInstanceShouldReturnSameInformation() {
        //Arrange
        double value = 5.0;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        //Act
        ElectricConsumptionWhValue clonedElectricConsumptionWhValue = electricConsumptionWhValue.clone();
        //Assert
        assertEquals(electricConsumptionWhValue.toString(), clonedElectricConsumptionWhValue.toString());
    }
    @Test
    void clonedInstanceShouldPointToDifferentMemoryLocation() {
        //Arrange
        double value = 5.0;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        //Act
        ElectricConsumptionWhValue clonedElectricConsumptionWhValue = electricConsumptionWhValue.clone();
        //Assert
        assertNotSame(electricConsumptionWhValue, clonedElectricConsumptionWhValue);
    }


}