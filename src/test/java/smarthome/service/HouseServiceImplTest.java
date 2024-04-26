package smarthome.service;

import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.persistence.mem.HouseRepository;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HouseServiceImplTest {

    /**
     * Test that the HouseService class can be instantiated.
     */
    @Test
    void shouldInstantiateValidHouse() {
        // Arrange
        IHouseFactory houseFactoryDouble = mock(IHouseFactory.class);
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);

        // Act
        HouseServiceImpl result = new HouseServiceImpl(houseFactoryDouble, houseRepositoryDouble);

        // Assert
        assertNotNull(result);
    }

    /**
     * Test that the HouseService class throws an IllegalArgumentException when the HouseFactory is null.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenHouseFactoryIsNull() {
        // Arrange
        IHouseFactory houseFactory = null;
        HouseRepository houseRepository = mock(HouseRepository.class);
        String expectedMessage = "House factory is required";
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HouseServiceImpl(houseFactory, houseRepository));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the HouseService class throws an IllegalArgumentException when the HouseRepository is null.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenHouseRepositoryIsNull() {
        // Arrange
        IHouseFactory houseFactory = mock(IHouseFactory.class);
        HouseRepository houseRepository = null;
        String expectedMessage = "House repository is required";
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HouseServiceImpl(houseFactory, houseRepository));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the addHouse method returns a House instance when the HouseFactory and HouseRepository are valid.
     */

    @Test
    void shouldReturnHouseInstanceWhenHouseFactoryAndHouseRepositoryAreValid() {
        // Arrange
        IHouseFactory houseFactory = mock(IHouseFactory.class);
        HouseRepository houseRepository = mock(HouseRepository.class);
        House house = mock(House.class);
        Address address = mock(Address.class);
        GPS gps = mock(GPS.class);
        when(houseFactory.createHouse(address, gps)).thenReturn(house);
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
        // Act
        House result = houseServiceImpl.addHouse(address, gps);
        // Assert
        assertEquals(house, result);
    }

}
