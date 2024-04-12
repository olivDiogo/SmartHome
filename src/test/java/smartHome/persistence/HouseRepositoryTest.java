package smartHome.persistence;

import smartHome.domain.house.House;
import smartHome.persistence.mem.HouseRepository;
import smartHome.valueObject.HouseID;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the HouseRepository class.
 */
class HouseRepositoryTest {

    /**
     * Tests the save method of the HouseRepository when given a valid house.
     */
    @Test
    void shouldSaveHouseWhenGivenValidHouse() {
        // Arrange
        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        HouseRepository houseRepository = new HouseRepository();

        // Act
        House savedHouse = houseRepository.save(house);

        // Assert
        assertEquals(house, savedHouse);
    }

    /**
     * Tests the save method of the HouseRepository when given a null house.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenNullHouse() {
        // Arrange
        House house = null;
        HouseRepository houseRepository = new HouseRepository();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> houseRepository.save(house));
        assertEquals("House cannot be null.", exception.getMessage());
    }

    /**
     * Tests the save method of the HouseRepository when a house with the same ID already exists.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenHouseAlreadyExists() {
        // Arrange
        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        HouseRepository houseRepository = new HouseRepository();

        houseRepository.save(house);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> houseRepository.save(house));
        assertEquals("House already exists.", exception.getMessage());
    }

    /**
     * Tests the findAll method of the HouseRepository when houses are saved.
     */
    @Test
    void shouldReturnAllHousesWhenFindAllIsCalled() {
        // Arrange
        House firstHouse = mock(House.class);
        HouseID firstHouseID = mock(HouseID.class);
        when(firstHouse.getID()).thenReturn(firstHouseID);
        House secondHouse = mock(House.class);
        HouseID secondHouseID = mock(HouseID.class);
        when(secondHouse.getID()).thenReturn(secondHouseID);

        HouseRepository houseRepository = new HouseRepository();

        houseRepository.save(firstHouse);
        houseRepository.save(secondHouse);
        List<House> expectedList = List.of(firstHouse, secondHouse);

        // Act
        List<House> allHouses = houseRepository.findAll();

        // Assert
        assertEquals(expectedList, allHouses);
    }

    /**
     * Tests the findAll method of the HouseRepository when no houses are saved.
     */
    @Test
    void shouldReturnEmptyListWhenNoHousesAreSaved() {
        // Arrange
        HouseRepository houseRepository = new HouseRepository();

        // Act
        List<House> allHouses = houseRepository.findAll();

        // Assert
        assertTrue(allHouses.isEmpty());
    }

    /**
     * Tests the ofIdentity method of the HouseRepository when given a valid house ID.
     */
    @Test
    void shoudReturnHouseWhenGivenValidHouseID() {
        // Arrange
        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        HouseRepository houseRepository = new HouseRepository();
        houseRepository.save(house);

        // Act
        House returnedHouse = houseRepository.ofIdentity(houseID).get();

        // Assert
        assertEquals(house, returnedHouse);
    }

    /**
     * Tests the ofIdentity method of the HouseRepository when given an invalid house ID.
     */
    @Test
    void shouldReturnOptinalEmptyWhenGivenInvalidHouseID() {
        // Arrange
        HouseRepository houseRepository = new HouseRepository();

        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        houseRepository.save(house);

        HouseID nonExistentHouseID = mock(HouseID.class);

        // Act
        Optional<House> returnedHouse = houseRepository.ofIdentity(nonExistentHouseID);

        // Assert
        assertTrue(returnedHouse.isEmpty());
    }

    /**
     * Tests the containsOfIdentity method of the HouseRepository when given a valid house ID.
     */
    @Test
    void shouldReturnTrueWhenGivenValidHouseID() {
        // Arrange
        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        HouseRepository houseRepository = new HouseRepository();
        houseRepository.save(house);

        // Act
        boolean containsHouse = houseRepository.containsOfIdentity(houseID);

        // Assert
        assertTrue(containsHouse);
    }

    /**
     * Tests the containsOfIdentity method of the HouseRepository when given an invalid house ID.
     */
    @Test
    void shouldReturnFalseWhenGivenInvalidHouseID() {
        // Arrange
        HouseRepository houseRepository = new HouseRepository();

        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        houseRepository.save(house);

        HouseID nonExistentHouseID = mock(HouseID.class);

        // Act
        boolean containsHouse = houseRepository.containsOfIdentity(nonExistentHouseID);

        // Assert
        assertFalse(containsHouse);
    }
}

