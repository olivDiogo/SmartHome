package smart_home.mapper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import smart_home.domain.house.House;
import smart_home.dto.HouseDTO;
import smart_home.value_object.Address;
import smart_home.value_object.GPS;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HouseAssemblerTest {

  /** Test that the HouseAssembler class can convert a House object to a HouseDTO object. */
  @Test
  void shouldReturnAHouseDTOWhenGivenAHouse() {
    // Arrange
    String address = "Test Address, 1";
    String gps = "GPS{latitude=90.0, longitude=180.0}";

    House house = mock(House.class);
    when(house.getAddress()).thenReturn(mock(Address.class));
    when(house.getAddress().toString()).thenReturn(address);
    when(house.getGps()).thenReturn(mock(GPS.class));
    when(house.getGps().toString()).thenReturn(gps);

    HouseAssembler houseAssembler = new HouseAssembler();
    String expected = address + " " + gps;

    // Act
    HouseDTO result = houseAssembler.domainToDTO(house);

    // Assert
    assertEquals(expected, result.toString());
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenHouseIsNull() {
    // Arrange
    House house = null;
    HouseAssembler houseAssembler = new HouseAssembler();
    String expectedMessage = "The House cannot be null.";

    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> houseAssembler.domainToDTO(house));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the HouseAssembler class can convert a House object list to a HouseDTO object list.
   */
  @Test
  void shouldReturnANewHouseDTOListWhenGivenAListOfHouses() {
    // Arrange
    String address = "Test Address, 1";
    String gps = "GPS{latitude=90.0, longitude=180.0}";

    String address2 = "Test Address2, 2";
    String gps2 = "GPS{latitude=90.0, longitude=170.0}";

    House house = mock(House.class);

    Address addressMock = mock(Address.class);
    GPS gpsMock = mock(GPS.class);

    when(house.getAddress()).thenReturn(addressMock);
    when(addressMock.toString()).thenReturn(address);
    when(house.getGps()).thenReturn(gpsMock);
    when(gpsMock.toString()).thenReturn(gps);

    House house2 = mock(House.class);
    Address addressMock2 = mock(Address.class);
    GPS gpsMock2 = mock(GPS.class);

    when(house2.getAddress()).thenReturn(addressMock2);
    when(addressMock2.toString()).thenReturn(address2);
    when(house2.getGps()).thenReturn(gpsMock2);
    when(gpsMock2.toString()).thenReturn(gps2);

    ArrayList<House> houses = new ArrayList<>();
    houses.add(house);
    houses.add(house2);

    HouseDTO houseDTO = new HouseDTO(address, gps);
    HouseDTO houseDTO2 = new HouseDTO(address2, gps2);
    List<HouseDTO> expected = List.of(houseDTO, houseDTO2);

    HouseAssembler houseAssembler = new HouseAssembler();

    // Act
    List<HouseDTO> result = houseAssembler.domainToDTO(houses);

    // Assert
    assertEquals(expected.toString(), result.toString());
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenHouseListIsNull() {
    // Arrange
    List<House> houses = null;
    HouseAssembler houseAssembler = new HouseAssembler();
    String expectedMessage = "The list of Houses cannot be null or empty.";

    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> houseAssembler.domainToDTO(houses));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenHouseListIsEmpty() {
    // Arrange
    List<House> houses = new ArrayList<>();
    HouseAssembler houseAssembler = new HouseAssembler();
    String expectedMessage = "The list of Houses cannot be null or empty.";

    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> houseAssembler.domainToDTO(houses));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenHouseListContainsNull() {
    // Arrange
    House house = mock(House.class);
    List<House> houses = new ArrayList<>();
    houses.add(house);
    houses.add(null);
    HouseAssembler houseAssembler = new HouseAssembler();
    String expectedMessage = "The list of Houses cannot be null or empty.";

    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> houseAssembler.domainToDTO(houses));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }
}
