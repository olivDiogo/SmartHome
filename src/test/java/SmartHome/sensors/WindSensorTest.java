package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WindSensorTest {

    /**
     * Testing WindSensor constructor with valid description
     * @throws InstantiationException
     */
    @Test
    void shouldInstantiateValidObject_WhenDescriptionIsValid() throws  InstantiationException{
        //Arrange
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);

        //Act
        new WindSensor(catalogueDouble);
    }

    /**
     * Testing WindSensor constructor with null argument
     * @throws InstantiationException
     */
    @Test
    void shouldThrowError_WhenDescriptionIsInvalid() {
        //Arrange
        CatalogueSensor mockCatalogue = mock(CatalogueSensor.class);
        when(mockCatalogue.getSensorType("WindSpeedAndDirection")).thenReturn(null);
        //Act + Assert
        assertThrows(InstantiationException.class, () -> {
            new WindSensor(mockCatalogue);
        });
    }

    /**
     * Testing getSensorType method with correct sensor type
     * @throws InstantiationException
     */
    @Test
    void shouldReturnValidSensorType_WhenDescriptionIsValid() throws InstantiationException {
        //Arrange
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);
        WindSensor windSensor = new WindSensor(catalogueDouble);

        //Act
        SensorType result = windSensor.getSensorType();

        //Assert
        assertEquals(sensorTypeDouble, result);
    }

    /**
     * Testing getValue method
     * @throws InstantiationException
     */
    @Test
    void shouldReturnValidValue() throws InstantiationException {
        //Arrange
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<WindSensorValue> mockedConstruction = Mockito.mockConstruction(WindSensorValue.class)) {
            WindSensor windSensor = new WindSensor(catalogueDouble);
            //Act
            windSensor.getValue();

            //Assert
            assertEquals(1, mockedConstruction.constructed().size());
        }
    }
    /**
     * Testing getValue method speed is within bounds
     * @throws InstantiationException
     */

    @Test
    void shouldReturnValidWindSpeed() throws InstantiationException {
        //Arrange
        double speed = 10.0;
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);
        WindSensor windSensor = new WindSensor(catalogueDouble);

        try(MockedConstruction<WindSensorValue> windSensorValueDouble = mockConstruction(WindSensorValue.class,(mock, context)->{
            when(mock.getSpeed()).thenReturn(speed);
        })){
            //Act
            windSensor.getValue();

            //Assert
            assertEquals(speed, windSensorValueDouble.constructed().get(0).getSpeed());
        }
    }

    /**
     * Testing getValue method direction is within bounds
     */
    @Test
    void shouldReturnValidDirection() throws InstantiationException {
        //Arrange
        Double directionRadians = 0.5;
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        Random mockRandom = mock(Random.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);
        when(mockRandom.nextDouble()).thenReturn(directionRadians);
        WindSensor windSensor = new WindSensor(catalogueDouble);

        try(MockedConstruction<WindSensorValue> windSensorValueDouble = mockConstruction(WindSensorValue.class,(mock, context)->{
            when(mock.getDirection()).thenReturn(directionRadians);
        })){
            //Act
            windSensor.getValue();

            //Assert
            assertEquals(directionRadians, windSensorValueDouble.constructed().get(0).getDirection());
        }
    }
}