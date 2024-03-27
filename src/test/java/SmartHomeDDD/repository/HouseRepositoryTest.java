package SmartHomeDDD.repository;

import SmartHomeDDD.valueObject.HouseID;
import SmartHomeDDD.domain.House.House;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HouseRepositoryTest {
    @Test
    void shouldInstantiateHouseRepository() {
        new HouseRepository();
    }

    @Test
    void shouldSaveHouseWhenGivenValidHouse() {
        //Arrange
        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        HouseRepository houseRepository = new HouseRepository();
        //Act
        House savedHouse = houseRepository.save(house);
        //Assert
        assertEquals(house, savedHouse);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenNullHouse() {
        //Arrange
        House house = null;
        HouseRepository houseRepository = new HouseRepository();
        String expectedMessage = "House cannot be null.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> houseRepository.save(house));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenHouseAlreadyExists() {
        //Arrange
        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        HouseRepository houseRepository = new HouseRepository();

        houseRepository.save(house);
        String expectedMessage = "House already exists.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> houseRepository.save(house));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturnAllHousesWhenFindAllIsCalled() {
        //Arrange
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
        //Act
        List<House> allHouses = houseRepository.findAll();
        //Assert
        assertEquals(expectedList, allHouses);
    }

    @Test
    void shouldReturnEmptyListWhenNoHousesAreSaved() {
        //Arrange
        HouseRepository houseRepository = new HouseRepository();
        //Act
        List<House> allHouses = houseRepository.findAll();
        //Assert
        assertTrue(allHouses.isEmpty());
    }

    @Test
    void shoudReturnHouseWhenGivenValidHouseID() {
        //Arrange
        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        HouseRepository houseRepository = new HouseRepository();
        houseRepository.save(house);
        //Act
        House returnedHouse = houseRepository.ofIdentity(houseID).get();
        //Assert
        assertEquals(house, returnedHouse);
    }

    @Test
    void shouldReturnOptinalEmptyWhenGivenInvalidHouseID() {
        //Arrange
        HouseRepository houseRepository = new HouseRepository();

        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        houseRepository.save(house);

        HouseID nonExistentHouseID = mock(HouseID.class);
        //Act
        Optional<House> returnedHouse = houseRepository.ofIdentity(nonExistentHouseID);
        //Assert
        assertTrue(returnedHouse.isEmpty());
    }

    @Test
    void shouldReturnTrueWhenGivenValidHouseID() {
        //Arrange
        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        HouseRepository houseRepository = new HouseRepository();
        houseRepository.save(house);
        //Act
        boolean containsHouse = houseRepository.containsOfIdentity(houseID);
        //Assert
        assertTrue(containsHouse);
    }

    @Test
    void shouldReturnFalseWhenGivenInvalidHouseID() {
        //Arrange
        HouseRepository houseRepository = new HouseRepository();

        House house = mock(House.class);
        HouseID houseID = mock(HouseID.class);
        when(house.getID()).thenReturn(houseID);
        houseRepository.save(house);

        HouseID nonExistentHouseID = mock(HouseID.class);
        //Act
        boolean containsHouse = houseRepository.containsOfIdentity(nonExistentHouseID);
        //Assert
        assertFalse(containsHouse);
    }

}