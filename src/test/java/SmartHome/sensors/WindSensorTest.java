package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WindSensorTest {

    /**
     * Testing WindSensor constructor with valid description
     * @throws InstantiationException
     */
    @Test
    void newValidWindSensor() throws  InstantiationException{
        //Arrange
        String strDescription = "WindSpeedAndDirection";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(strDescription)).thenReturn(sensorTypeDouble);

        //Act
        WindSensor windSensor = new WindSensor(catalogueDouble);
    }

    /**
     * Testing WindSensor constructor with null argument
     * @throws InstantiationException
     */
    @Test
    void constructor_throwsInstantiationException() {
        //Arrange
        CatalogueSensor mockCatalogue = mock(CatalogueSensor.class);
        when(mockCatalogue.getSensorType("WindSpeedAndDirection")).thenReturn(null);
        //Act + Assert
        assertThrows(InstantiationException.class, () -> {
            new WindSensor(mockCatalogue);
        });
    }


}