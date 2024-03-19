package SmartHomeDDD.domain.House;

import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.ZipCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Tests for the {@link ImpHouseFactory} class, ensuring that houses are created correctly under various conditions
 * and that appropriate exceptions are thrown when invalid parameters are provided.
 */
class ImpHouseFactoryTest {

    /**
     * Test to ensure that a House can be created successfully when {@link ImpHouseFactory#createHouse(Address, ZipCode, GPS)}
     * is called with valid parameters. This test verifies that no exceptions are thrown during the creation process.
     */
    @Test
    void shouldCreateHouseWhenCreateHouseIsCalledWithValidParameters(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);
        ImpHouseFactory factory = mock(ImpHouseFactory.class);

        // Act & Assert
        factory.createHouse(address, zipCode, gps);
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when {@link ImpHouseFactory#createHouse(Address, ZipCode, GPS)}
     * is called with a null Address parameter. This test confirms the robustness of the factory's parameter validation.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreateHouseIsCalledWithNullAddress(){
        // Arrange
        Address address = null;
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);
        ImpHouseFactory factory = new ImpHouseFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createHouse(address, zipCode, gps), "Factory should throw IllegalArgumentException for null Address.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when {@link ImpHouseFactory#createHouse(Address, ZipCode, GPS)}
     * is called with a null ZipCode parameter. This verifies that the factory properly checks for null values in its arguments.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreateHouseIsCalledWithNullZipCode(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = null;
        GPS gps = mock(GPS.class);
        ImpHouseFactory factory = new ImpHouseFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createHouse(address, zipCode, gps), "Factory should throw IllegalArgumentException for null ZipCode.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when {@link ImpHouseFactory#createHouse(Address, ZipCode, GPS)}
     * is called with a null GPS parameter. This test checks that all critical parameters are validated before house creation.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreateHouseIsCalledWithNullGPS(){
        // Arrange
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = null;
        ImpHouseFactory factory = new ImpHouseFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createHouse(address, zipCode, gps), "Factory should throw IllegalArgumentException for null GPS.");
    }
}
