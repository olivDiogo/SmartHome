package SmartHomeDDD.domain.House;

import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.HouseID;
import SmartHomeDDD.ValueObject.ZipCode;
import SmartHomeDDD.domain.House.House;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HouseTest {

    /**
     * Verifies that a House instance can be successfully instantiated with valid parameters.
     * This test does not expect any exceptions to be thrown during the instantiation process.
     */
    @Test
    void shouldInstantiateHouse_WhenConstructorIsCalledWithValidParameters(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);

        // Act & Assert
        new House(address, zipCode, gps);
    }

    /**
     * Ensures that an IllegalArgumentException is thrown when attempting to instantiate
     * a House with a null Address parameter, validating input parameter checks.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullAddress(){
        // Arrange
        Address address = null;
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new House(address, zipCode, gps));
    }

    /**
     * Ensures that an IllegalArgumentException is thrown when attempting to instantiate
     * a House with a null ZipCode parameter, validating input parameter checks.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullZipCode(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = null;
        GPS gps = mock(GPS.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new House(address, zipCode, gps));
    }

    /**
     * Ensures that an IllegalArgumentException is thrown when attempting to instantiate
     * a House with a null GPS parameter, validating input parameter checks.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullGPS(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new House(address, zipCode, gps));
    }

    /**
     * Tests that the {@link House#getID()} method returns the correct HouseID of the instantiated House.
     * This test verifies that the HouseID is properly set during House construction.
     */
    @Test
    void shouldReturnHouseID_WhenGetIDIsCalled(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);
        House house = new House(address, zipCode, gps);

        // Act
        HouseID result = house.getID();

        // Assert
        assertTrue(house.toString().contains(result.toString()));
    }

    /**
     * Tests that the {@link House#getAddress()} method returns the correct Address of the instantiated House.
     * This test verifies that the Address is properly set during House construction.
     */
    @Test
    void shouldReturnAddress_WhenGetAddressIsCalled(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);
        House house = new House(address, zipCode, gps);

        // Act
        Address result = house.getAddress();

        // Assert
        assertEquals(address, result);
    }

    /**
     * Tests that the {@link House#getZipCode()} method returns the correct ZipCode of the instantiated House.
     * This test verifies that the ZipCode is properly set during House construction.
     */
    @Test
    void shouldReturnZipCode_WhenGetZipCodeIsCalled(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);
        House house = new House(address, zipCode, gps);

        // Act
        ZipCode result = house.getZipCode();

        // Assert
        assertEquals(zipCode, result);
    }

    /**
     * Tests that the {@link House#getGps()} method returns the correct GPS of the instantiated House.
     * This test verifies that the GPS is properly set during House construction.
     */
    @Test
    void shouldReturnGps_WhenGetGpsIsCalled(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);
        House house = new House(address, zipCode, gps);

        // Act
        GPS result = house.getGps();

        // Assert
        assertEquals(gps, result);
    }

    /**
     * Confirms that two House instances with mocked HouseID values to be the same are considered equal.
     * This test utilizes mocking of the HouseID class to simulate identical IDs.
     */
    @Test
    void shouldReturnTrue_WhenTwoHousesHaveTheSameID() {
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);

        try (MockedConstruction<HouseID> houseIDMockedConstruction = mockConstruction(HouseID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("1");
        })) {
            House house = new House(address, zipCode, gps);
            House house2 = new House(address, zipCode, gps);

            // Act & Assert
            assertTrue(house.equals(house2), "A house should be equal to itself.");
        }
    }

    /**
     * Tests that two House instances with different HouseIDs are not considered equal.
     * This test ensures that the equals method correctly evaluates the identity of House instances.
     */
    @Test
    void shouldReturnFalse_WhenThereAreTwoDifferentHouses() {
        // Arrange
        Address address1 = mock(Address.class);
        ZipCode zipCode1 = mock(ZipCode.class);
        GPS gps1 = mock(GPS.class);

        Address address2 = mock(Address.class);
        ZipCode zipCode2 = mock(ZipCode.class);
        GPS gps2 = mock(GPS.class);

        House house1 = new House(address1, zipCode1, gps1);
        House house2 = new House(address2, zipCode2, gps2);

        // Act & Assert
        assertFalse(house1.equals(house2), "Houses with different IDs should not be equal.");
    }

    /**
     * Tests that the {@link House#toString()} method returns a string representation of the House instance.
     * This test verifies that the string includes the class name, along
     * with the HouseID, Address, ZipCode, and GPS properties.
     */
    @Test
    void shouldReturnStringRepresentation_WhenToStringIsCalled(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);
        House house = new House(address, zipCode, gps);

        // Act
        String result = house.toString();

        // Assert
        assertTrue(result.contains("House{"));
        assertTrue(result.contains("_houseID="));
        assertTrue(result.contains("_address="));
        assertTrue(result.contains("_zipCode="));
        assertTrue(result.contains("_gps="));
    }
}