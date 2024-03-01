package SmartHome.domain;

import SmartHome.controller.AddSensorToDeviceController;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CatalogueSensorTest {
    @Test
    void newConfiguredCatalogueFromExistingFile() throws InstantiationException, ConfigurationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);

        Configurations configs = new Configurations();
        Configuration config = configs.properties("config.properties");
        int supportedSensors = config.getStringArray("sensor").length;

        // act
        CatalogueSensor catalogueFromPath = new CatalogueSensor( "config.properties" );

        // assert
        assertEquals(supportedSensors, catalogueFromPath.getSensorModels().size());
    }

    @Test
    void newConfiguredCatalogueFromNonExistingFile()
    {
        // arrange
        String expectedMessage = "something went wrong in reading the configuration: ";

        // act + assert
        Exception exception = assertThrows( InstantiationException.class, () ->
                new CatalogueSensor( "asdfasdfasdf" )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    void NewConfiguredEmptyCatalogue()
//    {
//        // arrange
//        Configuration config = new PropertyListConfiguration();
//
//        // act
//        Catalogue catalogue = new Catalogue( config );
//
//        // assert
//        assertEquals( catalogue.getSensorModels().size(), 0);
//    }

    @Test
    void getExistingSensorType() throws InstantiationException
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
    void whenGetEmptySensorTypeList_thenReturnsNull() throws InstantiationException
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
    void addValidSensorType() throws InstantiationException
    {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeFactory sensorTypeFactory = mock(SensorTypeFactory.class);

        String strDescription = "Humidity";

        when(sensorTypeFactory.createSensorType(strDescription, Unit.Humidity)).thenReturn(sensorTypeDouble);
        when(sensorTypeDouble.getDescription()).thenReturn(strDescription);

        // act
        SensorType sensorType = catalogue.addSensorType(strDescription, Unit.Humidity, sensorTypeFactory);

        // assert
        assertEquals(sensorType, sensorTypeDouble);
    }

    @Test
    void addEmptyDescriptionSensorType_thenThrowsException() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorTypeFactory sensorTypeFactory = mock(SensorTypeFactory.class);

        String strDescription = "";

        when(sensorTypeFactory.createSensorType(strDescription, Unit.Humidity)).thenThrow(new InstantiationException());

        // act + assert
        assertThrows( InstantiationException.class, () ->
                catalogue.addSensorType(strDescription, Unit.Humidity, sensorTypeFactory)
        );
    }

    @Test
    void addNullDescriptionSensorType() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorTypeFactory sensorTypeFactory = mock(SensorTypeFactory.class);

        String strDescription = null;

        when(sensorTypeFactory.createSensorType(strDescription, Unit.Humidity)).thenThrow(new InstantiationException());


        // act + assert
        assertThrows( InstantiationException.class, () ->
                catalogue.addSensorType(null, Unit.Humidity, sensorTypeFactory)
        );
    }

    @Test
    void getSensorOfUniqueModel()  throws InstantiationException
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
    }


    @Test
    void getNullSensorOfEmptyListOfModels() throws InstantiationException
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

}