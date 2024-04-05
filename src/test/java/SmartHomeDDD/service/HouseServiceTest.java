package SmartHomeDDD.service;

import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.House.IHouseFactory;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.valueObject.Address;
import SmartHomeDDD.valueObject.GPS;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HouseServiceTest {

    /**
     * Test that the HouseService class can be instantiated.
     */
    @Test
    public void shouldInstantiateValidHouse() {
        // Arrange
        IHouseFactory houseFactoryDouble = mock(IHouseFactory.class);
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);

        // Act
        HouseService result = new HouseService(houseFactoryDouble, houseRepositoryDouble);

        // Assert
        assertNotNull(result);
    }

    /**
     * Test that the HouseService class throws an IllegalArgumentException when the HouseFactory is null.
     */
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenHouseFactoryIsNull() {
        // Arrange
        IHouseFactory houseFactory = null;
        HouseRepository houseRepository = mock(HouseRepository.class);
        String expectedMessage = "HouseFactory cannot be null.";
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HouseService(houseFactory, houseRepository));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the HouseService class throws an IllegalArgumentException when the HouseRepository is null.
     */
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenHouseRepositoryIsNull() {
        // Arrange
        IHouseFactory houseFactory = mock(IHouseFactory.class);
        HouseRepository houseRepository = null;
        String expectedMessage = "HouseRepository cannot be null.";
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HouseService(houseFactory, houseRepository));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the addHouse method returns a House instance when the HouseFactory and HouseRepository are valid.
     */

    @Test
    public void shouldReturnHouseInstanceWhenHouseFactoryAndHouseRepositoryAreValid() {
        // Arrange
        IHouseFactory houseFactory = mock(IHouseFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        House house = mock(House.class);
        Address address = mock(Address.class);
        GPS gps = mock(GPS.class);
        when(houseFactory.createHouse(address, gps)).thenReturn(house);
        HouseService houseService = new HouseService(houseFactory, houseRepository);
        // Act
        House result = houseService.addHouse(address, gps);
        // Assert
        assertEquals(house, result);
    }

}
