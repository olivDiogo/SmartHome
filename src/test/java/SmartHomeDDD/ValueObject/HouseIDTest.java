package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class HouseIDTest {

    /**
     * Tests the correct instantiation of a HouseID
     */
    @Test
    public void shouldGetValidObject_whenUsingValidStringInConstructor() {
        // Arrange
        String houseID = "HouseXPTO";
        // Act
        new HouseID(houseID);
        // Assert
    }

    /**
     * Tests if the exception is thrown with a null houseID
     */
    @Test
    public void shouldThrowException_whenHouseIdIsNull(){
        // Arrange
        String houseID = null;
        String expectedMessage = "Valor do parâmetro 'houseID' deve ser uma string não vazia.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new HouseID(houseID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with a blank houseID
     */
    @Test
    public void shouldThrowException_whenHouseIdIsBlank(){
        // Arrange
        String houseID = " ";
        String expectedMessage = "Valor do parâmetro 'houseID' deve ser uma string não vazia.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new HouseID(houseID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with an empty houseID
     */
    @Test
    public void shouldThrowException_whenHouseIdIsEmpty(){
        // Arrange
        String houseID = "";
        String expectedMessage = "Valor do parâmetro 'houseID' deve ser uma string não vazia.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new HouseID(houseID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the ID getter
     */
    @Test
    public void shouldGetHouseID(){
        // Arrange
        String idDescription = "HXPTO";
        HouseID houseID = new HouseID(idDescription);

        String expected = "HXPTO";

        // Act
        String result = houseID.getId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests if a houseID1 is equal to itself
     */
    @Test
    public void shouldReturnTrue_whenHouseIdIsEqualToItself(){
        // Arrange
        String idDescription = "HXPTO";
        HouseID houseID1 = new HouseID(idDescription);

        // Act
        boolean result = houseID1.equals(houseID1);

        // Assert
        assertTrue(result);
    }


    /**
     * Tests if a houseID1 is equal to a houseID2 if the ID of both is the same
     */
    @Test
    public void shouldReturnTrue_whenHouseIdIsEqualToOtherHouseId(){
        // Arrange
        String idDescription = "HXPTO";
        HouseID houseID1 = new HouseID(idDescription);
        HouseID houseID2 = new HouseID(idDescription);

        // Act
        boolean result = houseID1.equals(houseID2);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests if a houseID1 is not equal to a houseID2
     */
    @Test
    public void shouldReturnTrue_whenHouseIdIsNotEqualToAnotherHouseId(){
        // Arrange
        String idDescription1 = "HXPTO";
        HouseID houseID1 = new HouseID(idDescription1);

        String idDescription2 = "HRTHD";
        HouseID houseID2 = new HouseID(idDescription2);

        // Act
        boolean result = houseID1.equals(houseID2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnHashCode(){
        // Arrange
        String idDescription = "HXPTO";
        HouseID houseID = new HouseID(idDescription);

        int expected = idDescription.hashCode();

        // Act
        int result = houseID.hashCode();

        // Assert
        assertEquals(expected, result);
    }
}
