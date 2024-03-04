package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ElectricConsumptionWhSensorTest {
    @Test
    void shouldCreateElectricConsumptionWhSensorWithValidData() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        //Act
        new ElectricConsumptionWhSensor(catalogueDouble);
    }

    @Test
    void shouldReturnSensorType() throws InstantiationException {
        //Arrange
        String description = "ElectricConsumptionWh";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(catalogueDouble);
        //Act
        SensorType result = electricConsumptionWhSensor.getSensorType();
        //Assert
        assertEquals(sensorTypeDouble, result);
    }


}