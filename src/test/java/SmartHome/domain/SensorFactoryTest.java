package SmartHome.domain;


import static org.junit.jupiter.api.Assertions.assertNotNull;

class SensorFactoryTest {

        @org.junit.jupiter.api.Test
        void createSensor() throws InstantiationException {
            // arrange
            SensorFactory sensorFactory = new SensorFactory();
            CatalogueSensors catalogue = new CatalogueSensors("config.properties");
            catalogue.addSensorType("Humidity", Unit.Humidity, new SensorTypeFactory());
            String strModel = "SmartHome.sensors.TSY01";

            // act
            Sensor sensor = sensorFactory.createSensor(strModel, catalogue);

            // assert
            assertNotNull(sensor);
        }

}