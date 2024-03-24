package SmartHomeDDD.service;

import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.ZipCode;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.House.HouseFactory;
import SmartHomeDDD.repository.HouseRepository;
import org.junit.Test;

import java.util.List;

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
        HouseFactory houseFactoryDouble = mock(HouseFactory.class);
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);
        // Act
        new HouseService(houseFactoryDouble, houseRepositoryDouble);
    }

    /**
     * Test that the HouseService class throws an IllegalArgumentException when the HouseFactory is null.
     */
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenHouseFactoryIsNull() {
        // Arrange
        HouseFactory houseFactory = null;
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
        HouseFactory houseFactory = mock(HouseFactory.class);
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
        HouseFactory houseFactory = mock(HouseFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        House house = mock(House.class);
        Address address = mock(Address.class);
        ZipCode zipCode = mock(ZipCode.class);
        GPS gps = mock(GPS.class);
        when(houseFactory.createHouse(address, zipCode, gps)).thenReturn(house);
        HouseService houseService = new HouseService(houseFactory, houseRepository);
        // Act
        House result = houseService.addHouse(address, zipCode, gps);
        // Assert
        assertEquals(house, result);
    }

}
