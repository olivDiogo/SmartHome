package SmartHome.domain;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class DimensionsFactoryTest {

    @Test
    void shouldReturnMockedDimensionsWhenCreateDimensionsIsCalledWithValidParameters(){
        //Arrange
        double width = 1.0;
        double length = 1.0;
        double height = 1.0;

        try (MockedConstruction<Dimensions> mocked = Mockito.mockConstruction(Dimensions.class)) {
            //Act
            DimensionsFactory dimensionsFactory = new DimensionsFactory();
            Dimensions dimensions = dimensionsFactory.createDimensions(width, length, height);
            //Assert
            assertTrue(mocked.constructed().contains(dimensions));
        }
    }

    @Test
    void shouldReturnMockedDimensionsWithCorrectWidthWhenCreateDimensionsIsCalledWithValidParameters(){
        //Arrange
        double width = 1.0;
        double length = 1.0;
        double height = 1.0;

        try (MockedConstruction<Dimensions> mocked = Mockito.mockConstruction(Dimensions.class,(mock,context) -> {
            when(mock.getWidth()).thenReturn(width);
            when(mock.getLength()).thenReturn(length);
            when(mock.getHeight()).thenReturn(height);}) ){

            //Act
            DimensionsFactory dimensionsFactory = new DimensionsFactory();
            Dimensions dimensions = dimensionsFactory.createDimensions(width,length,height);
            //Assert
            assertEquals(width,dimensions.getWidth(),0.01);
        }
    }
}
