package smart_home.persistence.jpa.data_model;

import org.junit.jupiter.api.Test;
import smart_home.domain.house.House;
import smart_home.value_object.Address;
import smart_home.value_object.GPS;
import smart_home.value_object.HouseID;
import smart_home.value_object.IPostalCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HouseDataModelTest {
    /**
     * Test to check if the HouseDataModel is created with all the house information in the correct fields.
     */
    @Test
    void shouldCreateHouseDataModelWithAllHouseInformationInCorrectFields() {
        // Arrange
        String houseID = "1";
        double latitude = 10.0;
        double longitude = 20.0;
        String street = "Rua do Ouro";
        String doorNumber = "123";
        String countryCode = "PT";
        String postalCode = "4000-007";

        HouseID houseIDDouble = mock(HouseID.class);
        when(houseIDDouble.getID()).thenReturn(houseID);
        GPS gps = mock(GPS.class);
        when(gps.getLatitude()).thenReturn(latitude);
        when(gps.getLongitude()).thenReturn(longitude);
        Address address = mock(Address.class);
        when(address.getStreet()).thenReturn(street);
        when(address.getDoorNumber()).thenReturn(doorNumber);
        when(address.getCountryCode()).thenReturn(countryCode);
        IPostalCode postalCode1 = mock(IPostalCode.class);
        when(address.getPostalCode()).thenReturn(postalCode1);
        when(postalCode1.getCode()).thenReturn(postalCode);

        House house = mock(House.class);
        when(house.getID()).thenReturn(houseIDDouble);
        when(house.getGps()).thenReturn(gps);
        when(house.getAddress()).thenReturn(address);

        String expected = "HouseDataModel: " +
                "houseID='" + houseID + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", street='" + street + '\'' +
                ", doorNumber='" + doorNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", postalCode='" + postalCode + '.';
        // Act
        HouseDataModel houseDataModel = new HouseDataModel(house);
        // Assert
        assertEquals(expected, houseDataModel.toString());
    }

    /**
     * Test to check if the HouseDataModel throws an exception when the house passed is null.
     */
    @Test
    void shouldThrowExceptionWHenHouseIsNull() {
        // Arrange
        House house = null;
        String expected = "House cannot be null.";
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HouseDataModel(house));
        // Assert
        assertEquals(expected, exception.getMessage());
    }


}