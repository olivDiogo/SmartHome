package smartHome.persistence.jpa.dataModel;

import org.junit.jupiter.api.Test;
import smartHome.domain.sensorType.SensorType;
import smartHome.valueObject.SensorTypeID;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorTypeDataModelTest {

    @Test
    void shouldInstantiateSensorTypeDataModel() {
        //Arrange
        String strSensorTypeID = "123";
        String strTypeDescription = "DewPoint";
        String strUnitID = "Celsius";

        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);
        SensorType sensorTypeDouble = mock(SensorType.class);


        when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);
        when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
        when(unitIDDouble.getID()).thenReturn(strUnitID);

        when(sensorTypeDouble.getID()).thenReturn(sensorTypeIDDouble);
        when(sensorTypeDouble.getName()).thenReturn(typeDescriptionDouble);
        when(sensorTypeDouble.getUnit()).thenReturn(unitIDDouble);

        //Act
        SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel(sensorTypeDouble);

        //Assert
        assertNotNull(sensorTypeDataModel);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSensorTypeIsNull() {
        //Arrange
        SensorType sensorTypeDouble = null;

        String expectedMessage = "SensorType cannot be null";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorTypeDataModel(sensorTypeDouble));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldReturnSensorTypeID_WhenGetSensorTypeID() {
        //Arrange
        String strSensorTypeID = "123";
        String strTypeDescription = "DewPoint";
        String strUnitID = "Celsius";

        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);
        SensorType sensorTypeDouble = mock(SensorType.class);


        when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);
        when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
        when(unitIDDouble.getID()).thenReturn(strUnitID);

        when(sensorTypeDouble.getID()).thenReturn(sensorTypeIDDouble);
        when(sensorTypeDouble.getName()).thenReturn(typeDescriptionDouble);
        when(sensorTypeDouble.getUnit()).thenReturn(unitIDDouble);

        SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel(sensorTypeDouble);

        //Act
        String result = sensorTypeDataModel.getSensorTypeID();

        //Assert
        assertEquals(strSensorTypeID, result);

    }

    @Test
    void shouldReturnTypeDescription_WhenGetTypeDescription() {
        //Arrange
        String strSensorTypeID = "123";
        String strTypeDescription = "DewPoint";
        String strUnitID = "Celsius";

        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);
        SensorType sensorTypeDouble = mock(SensorType.class);


        when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);
        when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
        when(unitIDDouble.getID()).thenReturn(strUnitID);

        when(sensorTypeDouble.getID()).thenReturn(sensorTypeIDDouble);
        when(sensorTypeDouble.getName()).thenReturn(typeDescriptionDouble);
        when(sensorTypeDouble.getUnit()).thenReturn(unitIDDouble);

        SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel(sensorTypeDouble);

        //Act
        String result = sensorTypeDataModel.getTypeDescription();

        //Assert
        assertEquals(strTypeDescription, result);
    }

    @Test
    void shouldReturnUnitID_WhenGetUnitID() {
        //Arrange
        String strSensorTypeID = "123";
        String strTypeDescription = "DewPoint";
        String strUnitID = "Celsius";

        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);
        SensorType sensorTypeDouble = mock(SensorType.class);


        when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);
        when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
        when(unitIDDouble.getID()).thenReturn(strUnitID);

        when(sensorTypeDouble.getID()).thenReturn(sensorTypeIDDouble);
        when(sensorTypeDouble.getName()).thenReturn(typeDescriptionDouble);
        when(sensorTypeDouble.getUnit()).thenReturn(unitIDDouble);

        SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel(sensorTypeDouble);

        //Act
        String result = sensorTypeDataModel.getUnitID();

        //Assert
        assertEquals(strUnitID, result);
    }

    @Test
    void shouldInstantiateSensorTypeDataModelWithDefaultConstructor() {
        //Act
        SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel();

        //Act
        assertNotNull(sensorTypeDataModel);
    }
}