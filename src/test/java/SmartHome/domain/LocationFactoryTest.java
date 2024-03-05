package SmartHome.domain;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationFactoryTest {

    @Test
    void shouldReturnMockedLocation() {
        // Arrange
        String street = "Rua do Ouro";
        String zipCode = "1100-063";
        int doorNumber = 123;
        double latitude = 38.7167;
        double longitude = 9.1333;

        try (MockedConstruction<Address> mocked = Mockito.mockConstruction(Address.class)) {
            //Act
            LocationFactory locationFactory = new LocationFactory();
            Location location = locationFactory.createLocation(street, zipCode, doorNumber, latitude, longitude);
            //Assert
            assertTrue(mocked.constructed().contains(location.getAddress()));
        }
    }
}