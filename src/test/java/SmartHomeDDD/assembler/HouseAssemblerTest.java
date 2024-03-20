package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.HousesDTO;
import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.ZipCode;

import SmartHomeDDD.domain.House.House;
import org.junit.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
        when(house.getZipCode()).thenReturn(new ZipCode(1234, 100));
        when(house.getAddress()).thenReturn(new Address("Test Address", "1"));
        when(house.getGps()).thenReturn(new GPS(90, 180));
        int expected = 1;

        HouseAssembler houseAssembler = new HouseAssembler();

        try (MockedConstruction<HousesDTO> mocked = mockConstruction(HousesDTO.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {
            // Act
            HousesDTO result = houseAssembler.domainToDTO(house);

            // Assert
            List<HousesDTO> houseDTOList = mocked.constructed();
            assertEquals(result.toString(), houseDTOList.get(0).toString());
            assertEquals(expected, houseDTOList.size());
        }
    }

    @Test
    public void shouldReturnANewHouseDTOListWhenGivenHouses() {
        // Arrange
        House house = mock(House.class);
        when(house.getZipCode()).thenReturn(new ZipCode(1234, 100));
        when(house.getAddress()).thenReturn(new Address("Test Address", "1"));
        when(house.getGps()).thenReturn(new GPS(90, 180));

        House house2 = mock(House.class);
        when(house.getZipCode()).thenReturn(new ZipCode(1234, 101));
        when(house.getAddress()).thenReturn(new Address("Test Address", "2"));
        when(house.getGps()).thenReturn(new GPS(90, 170));

        ArrayList<House> houses = new ArrayList<>();
        houses.add(house);
        houses.add(house2);

        HouseAssembler houseAssembler = new HouseAssembler();

        try (MockedConstruction<HousesDTO> mocked = mockConstruction(HousesDTO.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().getClass().toString());
        })) {
            // Act
            List<HousesDTO> result = houseAssembler.domainToDTO(houses);

            // Assert
            List<HousesDTO> houseDTOList = mocked.constructed();
            assertEquals(houses.size(), houseDTOList.size());
            assertArrayEquals(result.toArray(), houseDTOList.toArray());
        }
    }

}

