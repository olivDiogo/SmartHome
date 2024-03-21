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

        try (MockedConstruction<HouseDTO> mocked = mockConstruction(HouseDTO.class, (mock, context) -> {
            String strAddress = (String) context.arguments().get(0);
            String strPostalCode = (String) context.arguments().get(1);
            String strGPS = (String) context.arguments().get(2);

            when(mock.getGPS()).thenReturn(strGPS);
            when(mock.getAddress()).thenReturn(strAddress);
            when(mock.getPostalCode()).thenReturn(strPostalCode);
        })) {
            // Act
            HouseDTO result = houseAssembler.domainToDTO(house);
            String address = result.getAddress();
            String postalCode = result.getPostalCode();
            String gps = result.getGPS();

            // Assert
            List<HouseDTO> houseDTOList = mocked.constructed();
            assertEquals(address, "Test Address, 1");
            assertEquals(postalCode, "1234-100");
            assertEquals(gps, "GPS{latitude=90.0, longitude=180.0}");
            assertEquals(result.toString(), houseDTOList.get(0).toString());
        }
    }

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

        try (MockedConstruction<HouseDTO> mocked = mockConstruction(HouseDTO.class, (mock, context) -> {

        })) {
            // Act
            List<HouseDTO> result = houseAssembler.domainToDTO(houses);

            // Assert
            List<HouseDTO> houseDTOList = mocked.constructed();
            assertArrayEquals(result.toArray(), houseDTOList.toArray());
            assertEquals(result.toString(), houseDTOList.toString());
            assertTrue(result.containsAll(houseDTOList));
        }
    }

}

