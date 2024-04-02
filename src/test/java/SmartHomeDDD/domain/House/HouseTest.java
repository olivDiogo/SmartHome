package SmartHomeDDD.domain.House;

import SmartHomeDDD.valueObject.Address;
import SmartHomeDDD.valueObject.GPS;
import SmartHomeDDD.valueObject.HouseID;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

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
        GPS gps = mock(GPS.class);

        // Act & Assert
        new House(address, gps);
    }

    /**
     * Ensures that an IllegalArgumentException is thrown when attempting to instantiate
     * a House with a null Address parameter, validating input parameter checks.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullAddress(){
        // Arrange
        Address address = null;
        GPS gps = mock(GPS.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new House(address, gps));
    }

    /**
     * Ensures that an IllegalArgumentException is thrown when attempting to instantiate
     * a House with a null GPS parameter, validating input parameter checks.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullGPS(){
        // Arrange
        Address address = mock(Address.class);
        GPS gps = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new House(address, gps));
    }

    /**
     * Tests that the {@link House#getID()} method returns the correct HouseID of the instantiated House.
     * This test verifies that the HouseID is properly set during House construction.
     */
    @Test
    void shouldReturnHouseID_WhenGetIDIsCalled(){
        // Arrange
        Address address = mock(Address.class);
        GPS gps = mock(GPS.class);
        House house = new House(address, gps);

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
        GPS gps = mock(GPS.class);
        House house = new House(address, gps);

        // Act
        Address result = house.getAddress();

        // Assert
        assertEquals(address, result);
    }

    /**
     * Tests that the {@link House#getGps()} method returns the correct GPS of the instantiated House.
     * This test verifies that the GPS is properly set during House construction.
     */
    @Test
    void shouldReturnGps_WhenGetGpsIsCalled(){
        // Arrange
        Address address = mock(Address.class);
        GPS gps = mock(GPS.class);
        House house = new House(address, gps);

        // Act
        GPS result = house.getGps();

        // Assert
        assertEquals(gps, result);
    }

    /**
     * Tests method equals of class House when the instance is compared to itself.
     */
    @Test
    void shouldReturnTrue_WhenComparedToItself() {
        // Arrange
        Address address = mock(Address.class);
        GPS gps = mock(GPS.class);

        House house = new House(address, gps);

        // Act
        boolean result = house.equals(house);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests that two House instances with different HouseIDs are not considered equal.
     * This test ensures that the equals method correctly evaluates the identity of House instances.
     */
    @Test
    void shouldReturnFalse_WhenThereAreTwoDifferentHouses() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        Address address1 = mock(Address.class);
        GPS gps1 = mock(GPS.class);

        Address address2 = mock(Address.class);
        GPS gps2 = mock(GPS.class);

        House house1 = new House(address1, gps1);
        House house2 = new House(address2, gps2);

        Field houseIDField = House.class.getDeclaredField("_houseID");
        houseIDField.setAccessible(true);
        houseIDField.set(house1, mock(HouseID.class));
        houseIDField.set(house2, mock(HouseID.class));

        // Act
        boolean result = house1.equals(house2);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests that two House instances with the same HouseID are considered equal.
     * This test ensures that the equals method correctly evaluates the identity of House instances.
     */
    @Test
    void shouldReturnTrue_WhenThereAreTwoEqualHouses() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        Address address = mock(Address.class);
        GPS gps = mock(GPS.class);

        House house1 = new House(address, gps);
        House house2 = new House(address, gps);

        HouseID houseID = mock(HouseID.class);

        Field houseIDField = House.class.getDeclaredField("_houseID");
        houseIDField.setAccessible(true);
        houseIDField.set(house1, houseID);
        houseIDField.set(house2, houseID);

        // Act
        boolean result = house1.equals(house2);

        // Assert
        assertTrue(result);
    }


    /**
     * Tests the equals method of class House when the instance is compared to a null object.
     */
    @Test
    void shouldReturnFalse_WhenComparedWithNull() {
        // Arrange
        Address address = mock(Address.class);
        GPS gps = mock(GPS.class);

        House house = new House(address, gps);

        // Act
        boolean isEqual = house.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    /**
     * Tests the equals method of class House when the instance is compared to an object of a different class.
     */
    @Test
    void shouldReturnFalse_WhenComparedWithDifferentClass() {
        // Arrange
        Address address = mock(Address.class);
        GPS gps = mock(GPS.class);

        House house = new House(address, gps);

        // Act
        boolean isEqual = house.equals(new Object());

        // Assert
        assertFalse(isEqual, "House should not be equal to an object of a different class");
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
        GPS gps = mock(GPS.class);
        House house = new House(address, gps);

        // Act
        String result = house.toString();

        // Assert
        assertTrue(result.contains("House{"));
        assertTrue(result.contains("_houseID="));
        assertTrue(result.contains("_address="));
        assertTrue(result.contains("_gps="));
    }
}