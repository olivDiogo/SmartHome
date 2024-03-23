package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.HouseDTO;
import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.ZipCode;

import SmartHomeDDD.domain.House.House;
import org.junit.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HouseAssemblerTest {
    @Test
    public void shouldInstantiateANewHouseAssembler() {
        new HouseAssembler();
    }


    /**
     * Test that the HouseAssembler class can convert a House object to a HouseDTO object.
     */
    @Test
    public void shouldReturnAHouseDTOWhenGivenAHouse() {
        // Arrange
        House house = mock(House.class);
        when(house.getZipCode()).thenReturn(mock(ZipCode.class));
        when(house.getZipCode().toString()).thenReturn("1234-100");
        when(house.getAddress()).thenReturn(mock(Address.class));
        when(house.getAddress().toString()).thenReturn("Test Address, 1");
        when(house.getGps()).thenReturn(mock(GPS.class));
        when(house.getGps().toString()).thenReturn("GPS{latitude=90.0, longitude=180.0}");

        HouseAssembler houseAssembler = new HouseAssembler();

        // Act
        HouseDTO result = houseAssembler.domainToDTO(house);

        // Assert
        assertEquals(result.address, "Test Address, 1");
        assertEquals(result.zipCode, "1234-100");
        assertEquals(result.gps, "GPS{latitude=90.0, longitude=180.0}");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenHouseIsNull() {
        // Arrange
        House house = null;
        HouseAssembler houseAssembler = new HouseAssembler();
        String expectedMessage = "The House cannot be null.";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> houseAssembler.domainToDTO(house));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the HouseAssembler class can convert a House object list to a HouseDTO object list.
     */
    @Test
    public void shouldReturnANewHouseDTOListWhenGivenAListOfHouses() {
        // Arrange
        House house = mock(House.class);
        when(house.getZipCode()).thenReturn(mock(ZipCode.class));
        when(house.getZipCode().toString()).thenReturn("1234-100");
        when(house.getAddress()).thenReturn(mock(Address.class));
        when(house.getAddress().toString()).thenReturn("Test Address, 1");
        when(house.getGps()).thenReturn(mock(GPS.class));
        when(house.getGps().toString()).thenReturn("GPS{latitude=90.0, longitude=180.0}");

        House house2 = mock(House.class);
        when(house2.getZipCode()).thenReturn(mock(ZipCode.class));
        when(house2.getZipCode().toString()).thenReturn("1234-101");
        when(house2.getAddress()).thenReturn(mock(Address.class));
        when(house2.getAddress().toString()).thenReturn("Test Address2, 2");
        when(house2.getGps()).thenReturn(mock(GPS.class));
        when(house2.getGps().toString()).thenReturn("GPS{latitude=90.0, longitude=170.0}");

        ArrayList<House> houses = new ArrayList<>();
        houses.add(house);
        houses.add(house2);

        HouseAssembler houseAssembler = new HouseAssembler();

        // Act
        List<HouseDTO> result = houseAssembler.domainToDTO(houses);

        // Assert
        assertEquals(result.get(0).address, "Test Address, 1");
        assertEquals(result.get(0).zipCode, "1234-100");
        assertEquals(result.get(0).gps, "GPS{latitude=90.0, longitude=180.0}");
        assertEquals(result.get(1).address, "Test Address2, 2");
        assertEquals(result.get(1).zipCode, "1234-101");
        assertEquals(result.get(1).gps, "GPS{latitude=90.0, longitude=170.0}");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenHouseListIsNull() {
        // Arrange
        List<House> houses = null;
        HouseAssembler houseAssembler = new HouseAssembler();
        String expectedMessage = "The list of Houses cannot be null or empty.";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> houseAssembler.domainToDTO(houses));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenHouseListIsEmpty() {
        // Arrange
        List<House> houses = new ArrayList<>();
        HouseAssembler houseAssembler = new HouseAssembler();
        String expectedMessage = "The list of Houses cannot be null or empty.";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> houseAssembler.domainToDTO(houses));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenHouseListContainsNull() {
        // Arrange
        House house = mock(House.class);
        List<House> houses = new ArrayList<>();
        houses.add(house);
        houses.add(null);
        HouseAssembler houseAssembler = new HouseAssembler();
        String expectedMessage = "The list of Houses cannot be null or empty.";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> houseAssembler.domainToDTO(houses));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}

