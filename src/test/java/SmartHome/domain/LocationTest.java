package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LocationTest {
    @Test
    void shouldReturnValidLocation() {
        // Arrange
        Address addressDouble = mock(Address.class);
        Gps gpsDouble = mock(Gps.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        GpsFactory gpsFactoryDouble = mock(GpsFactory.class);
        String street = "Rua do Ouro";
        String zipCode = "4000-000";
        int doorNumber = 123;
        double latitude = 41.14961;
        double longitude = -8.61099;

        when(addressFactoryDouble.createAddress(street, zipCode, doorNumber)).thenReturn(addressDouble);
        when(gpsFactoryDouble.createGps(latitude, longitude)).thenReturn(gpsDouble);
        // Act
        Location location = new Location(street, zipCode, doorNumber, latitude, longitude, addressFactoryDouble, gpsFactoryDouble);
        // Assert
        assertNotNull(location);
    }
    @Test
    void shouldReturnCorrectedAddress() {
        // Arrange
        Address addressDouble = mock(Address.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        GpsFactory gpsFactoryDouble = mock(GpsFactory.class);
        String street = "Rua do Ouro";
        String zipCode = "4000-000";
        int doorNumber = 123;
        double latitude = 41.14961;
        double longitude = -8.61099;

        when(addressFactoryDouble.createAddress(street, zipCode, doorNumber)).thenReturn(addressDouble);

        Location location = new Location(street, zipCode, doorNumber, latitude, longitude, addressFactoryDouble, gpsFactoryDouble);
        // Act
        Address address = location.getAddress();
        // Assert
        assertEquals(addressDouble, address);
    }
    @Test
    void shouldReturnCorrectedGpsLocation() {
        // Arrange
        Address addressDouble = mock(Address.class);
        Gps gpsDouble = mock(Gps.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        GpsFactory gpsFactoryDouble = mock(GpsFactory.class);
        String street = "Rua do Ouro";
        String zipCode = "4000-000";
        int doorNumber = 123;
        double latitude = 41.14961;
        double longitude = -8.61099;

        when(gpsFactoryDouble.createGps(latitude, longitude)).thenReturn(gpsDouble);

        Location location = new Location(street, zipCode, doorNumber, latitude, longitude, addressFactoryDouble, gpsFactoryDouble);
        // Act
        Gps gps = location.getGpsLocation();
        // Assert
        assertEquals(gpsDouble, gps);
    }
}