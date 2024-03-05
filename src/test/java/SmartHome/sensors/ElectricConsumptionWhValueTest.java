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
    void comparingCloneShouldReturnTrue() {
        //Arrange
        double value = 5.0;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        //Act
        ElectricConsumptionWhValue clonedElectricConsumptionWhValue = electricConsumptionWhValue.clone();
        //Assert
        assertEquals(electricConsumptionWhValue, clonedElectricConsumptionWhValue);
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
    @Test
    void shouldReturnFalseWhenComparingInstancesWithDifferentValues() {
        //Arrange
        double value = 5.0;
        double differentValue = 5.5;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        ElectricConsumptionWhValue electricConsumptionWhValueWithDifferentDouble = new ElectricConsumptionWhValue(differentValue);
        //Act
        boolean result = electricConsumptionWhValue.equals(electricConsumptionWhValueWithDifferentDouble);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnTrueWhenComparingSameInstances() {
        //Arrange
        double value = 5.0;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        //Act
        boolean result = electricConsumptionWhValue.equals(electricConsumptionWhValue);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseWhenComparingDifferentClasses() {
        //Arrange
        double value = 5.0;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        Object object = new Object();
        //Act
        boolean result = electricConsumptionWhValue.equals(object);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenComparingWithNull() {
        //Arrange
        double value = 5.0;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        //Act
        boolean result = electricConsumptionWhValue.equals(null);
        //Assert
        assertFalse(result);
    }

}