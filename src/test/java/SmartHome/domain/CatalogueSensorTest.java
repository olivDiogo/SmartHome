package SmartHome.domain;

import SmartHome.controller.AddSensorToDeviceController;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CatalogueSensorTest {
    @Test
    void shouldInstantiateCatalogueSensorWhenFilePathnameIsValid() throws InstantiationException, ConfigurationException {
        // Arrange
        String filePathname = "config.properties";
        // Act
        new CatalogueSensor( filePathname );
        // Assert
    }

    @Test
    void shouldThrowExceptionWhenFilePathnameIsInvalid()
    {
        // Arrange
        String expectedMessage = "something went wrong in reading the configuration: ";
        String filePathname = "asdfasdfasdf";
        // act + assert
        Exception exception = assertThrows( InstantiationException.class, () ->
                new CatalogueSensor( filePathname )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldReturnSensorTypeWhenDescriptionExists() throws InstantiationException
    {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorType sensorTypeDouble = mock(SensorType.class);

        String strDescription = "Humidity";

        catalogue.addSensorTypeToList(sensorTypeDouble);

        when(sensorTypeDouble.getDescription()).thenReturn(strDescription);

        // act
        SensorType sensorType = catalogue.getSensorType(strDescription);

        // assert
        assertEquals(sensorType, sensorTypeDouble);
    }
    @Test
    void shouldReturnNullWhenSensorTypeDoesNotExist() throws InstantiationException
    {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorType sensorTypeDouble = mock(SensorType.class);

        String strDescription = "Temperature";

        catalogue.addSensorTypeToList(sensorTypeDouble);

        when(sensorTypeDouble.getDescription()).thenReturn(strDescription);

        // act
        SensorType sensorType = catalogue.getSensorType("Humidity");

        // assert
        assertNull(sensorType);
    }


    @Test
    void shouldReturnNullWhenSensorTypeListIsEmpty() throws InstantiationException
    {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        String strDescription = "Temperature";

        // act
        SensorType sensorType = catalogue.getSensorType(strDescription);

        // assert
        assertNull(sensorType);
    }

    @Test
    void shouldAddSensorTypeWhenDescriptionAndUnitAreValid() throws InstantiationException
    {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeFactory sensorTypeFactory = mock(SensorTypeFactory.class);

        String strDescription = "Humidity";

        when(sensorTypeFactory.createSensorType(strDescription, Unit.HUMIDITY)).thenReturn(sensorTypeDouble);
        when(sensorTypeDouble.getDescription()).thenReturn(strDescription);

        // act
        SensorType sensorType = catalogue.addSensorType(strDescription, Unit.HUMIDITY, sensorTypeFactory);

        // assert
        assertEquals(sensorType, sensorTypeDouble);
    }
    @Test
    void shouldAddSensorTypeToListWhenSensorTypeIsValid() throws InstantiationException
    {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorType sensorTypeDouble = mock(SensorType.class);
        // act
        SensorType sensorType = catalogue.addSensorTypeToList(sensorTypeDouble);

        // assert
        List<SensorType> listSensorTypes = catalogue.getSensorTypes();

        assertTrue(listSensorTypes.contains(sensorType));
    }

    @Test
    void shouldThrowExceptionWhenAddingSensorTypeWithEmptyDescription() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorTypeFactory sensorTypeFactory = mock(SensorTypeFactory.class);

        String strDescription = "";

        when(sensorTypeFactory.createSensorType(strDescription, Unit.HUMIDITY)).thenThrow(new InstantiationException());

        // act + assert
        assertThrows( InstantiationException.class, () ->
                catalogue.addSensorType(strDescription, Unit.HUMIDITY, sensorTypeFactory)
        );
    }

    @Test
    void shouldThrowExceptionWhenAddingSensorTypeWithNullDescription() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorTypeFactory sensorTypeFactory = mock(SensorTypeFactory.class);

        String strDescription = null;

        when(sensorTypeFactory.createSensorType(strDescription, Unit.HUMIDITY)).thenThrow(new InstantiationException());


        // act + assert
        assertThrows( InstantiationException.class, () ->
                catalogue.addSensorType(null, Unit.HUMIDITY, sensorTypeFactory)
        );
    }

/*    @Test
    void shouldReturnSensorWhenModelIsValid()  throws InstantiationException
    {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        Sensor sensorDouble = mock(Sensor.class);
        SensorFactory sensorFactory = mock(SensorFactory.class);

        String strModel = "SmartHome.sensors.HumiditySensor";

        when(sensorFactory.createSensor(strModel, catalogue)).thenReturn(sensorDouble);

        // act
        Sensor sensor = catalogue.getSensor( strModel, sensorFactory);

        // assert
        assertEquals( sensor, sensorDouble );
    }*/


    @Test
    void shouldReturnNullWhenSensorModelListIsEmpty() throws InstantiationException
    {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorFactory sensorFactory = mock(SensorFactory.class);

        String strModel = "";

        // act
        Sensor sensor = catalogue.getSensor( strModel, sensorFactory );

        // assert
        assertNull( sensor );
    }
    @Test
    void shouldReturnNullWhenSensorModelDoesNotExist() throws InstantiationException
    {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorFactory sensorFactory = mock(SensorFactory.class);
        when(sensorFactory.createSensor("UnsupportedModel", catalogue)).thenReturn(null);

        String strModel = "UnsupportedModel";

        // act
        Sensor sensor = catalogue.getSensor( strModel, sensorFactory );

        // assert
        assertNull( sensor );
    }

}