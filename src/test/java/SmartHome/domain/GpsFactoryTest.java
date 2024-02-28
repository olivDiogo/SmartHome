package SmartHome.domain;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GpsFactoryTest {
    @Test
    void shouldReturnMockedGPS_TestWithoutWhen(){
        //Arrange
        double latitude = 22.3;
        double longitude = 33.4;

        try (MockedConstruction <Gps> mocked = Mockito.mockConstruction(Gps.class)){
            //Act
            GpsFactory gpsFactory = new GpsFactory();
            Gps gps = gpsFactory.createGps(latitude, longitude);

            //Assert
            assertTrue(mocked.constructed().contains(gps));
        }
    }

    @Test
    void shouldReturnMockedAddress_TestWithWhen (){
        //Arrange
        double latitude = 22.3;
        double longitude = 33.4;

        try (MockedConstruction<Gps> mocked = Mockito.mockConstruction(Gps.class, (mock, context) ->
        { when(mock.getLatitude()).thenReturn(latitude);
            when(mock.getLongitude()).thenReturn(longitude);
        })){
            //Act
            GpsFactory gpsFactory = new GpsFactory();
            Gps gps = gpsFactory.createGps(latitude, longitude);

            //Assert
            assertEquals(latitude, gps.getLatitude());
    }
    }
}

