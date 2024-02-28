package SmartHome.domain;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AddressFactoryTest {
    @Test
    void shouldReturnMockedAddress_TestWithoutWhen(){
        //Arrange
        String street = "Street";
        String zipCode = "12345";
        int doorNumber = 1;

        try (MockedConstruction<Address> mocked = Mockito.mockConstruction(Address.class)){
            //Act
            AddressFactory addressFactory = new AddressFactory();
            Address address = addressFactory.createAddress(street, zipCode, doorNumber);
            //Assert
            assertTrue(mocked.constructed().contains(address)
            );}
    }
    @Test
    void shouldReturnMockedAddress_TestWithWhen(){
        //Arrange
        String street = "Street";
        String zipCode = "12345";
        int doorNumber = 1;

        try (MockedConstruction<Address> mocked = Mockito.mockConstruction(Address.class, (mock,context) -> {
            when(mock.getStreet()).thenReturn(street);
            when(mock.getZipCode()).thenReturn(zipCode);
            when(mock.getDoorNumber()).thenReturn(doorNumber);}) ){
            //Act
            AddressFactory addressFactory = new AddressFactory();
            Address address = addressFactory.createAddress(street, zipCode, doorNumber);
            //Assert
            assertEquals(street, address.getStreet());
        }
        }
    }


