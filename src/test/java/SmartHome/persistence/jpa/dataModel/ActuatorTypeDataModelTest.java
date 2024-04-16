package smartHome.persistence.jpa.dataModel;

import org.junit.jupiter.api.Test;
import smartHome.domain.actuatorType.ActuatorType;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorTypeDataModelTest {

    @Test
    void shouldInstantiateActuatorTypeDataModel_WhenAttributesAreValid() {
        //Arrange
        String strActuatorTypeID = "123";
        String strTypeDescription = "DewPoint";
        String strUnitID = "Celsius";

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);


        when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
        when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
        when(unitIDDouble.getID()).thenReturn(strUnitID);

        when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
        when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
        when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

        //Act
        ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

        //Assert
        assertNotNull(actuatorTypeDataModel);
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIsNull() {
        //Arrange
        ActuatorType actuatorTypeDouble = null;

        String expectedMessage = "ActuatorType cannot be null";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorTypeDataModel(actuatorTypeDouble));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldReturnActuatorTypeID_WhenGetActuatorTypeID() {
        //Arrange
        String strActuatorTypeID = "123";
        String strTypeDescription = "DewPoint";
        String strUnitID = "Celsius";

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
        when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
        when(unitIDDouble.getID()).thenReturn(strUnitID);

        when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
        when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
        when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

        ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

        //Act
        String result = actuatorTypeDataModel.getActuatorTypeID();

        //Assert
        assertEquals(strActuatorTypeID, result);
    }

    @Test
    void shouldReturnActuatorTypeName_WhenGetActuatorTypeName() {
            //Arrange
            String strActuatorTypeID = "123";
            String strTypeDescription = "DewPoint";
            String strUnitID = "Celsius";

            ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
            UnitID unitIDDouble = mock(UnitID.class);
            ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

            when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
            when(typeDescriptionDouble.toString()).thenReturn(strTypeDescription);
            when(unitIDDouble.getID()).thenReturn(strUnitID);

            when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
            when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
            when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

            ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

            //Act
            String result = actuatorTypeDataModel.getActuatorTypeName();

            //Assert
            assertEquals(strTypeDescription, result);
        }

    @Test
    void shouldReturnUnitID_WhenGetUnitID() {
        //Arrange
        String strActuatorTypeID = "123";
        String strTypeDescription = "DewPoint";
        String strUnitID = "Celsius";

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
        when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
        when(unitIDDouble.getID()).thenReturn(strUnitID);

        when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
        when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
        when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

        ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

        //Act
        String result = actuatorTypeDataModel.getUnitID();

        //Assert
        assertEquals(strUnitID, result);
    }
}