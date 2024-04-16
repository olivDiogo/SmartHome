package smartHome.persistence.assembler;

import org.junit.jupiter.api.Test;
import smartHome.domain.actuatorType.ActuatorType;
import smartHome.domain.actuatorType.IActuatorTypeFactory;
import smartHome.persistence.jpa.dataModel.ActuatorTypeDataModel;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorTypeDataModelAssemblerTest {

    /**
     * Test to ensure that an exception is thrown when the ActuatorTypeFactory is null.
     */
    @Test
    void shouldThrowException_whenActuatorTypeFactoryIsNull() {
        // Arrange
        IActuatorTypeFactory actuatorTypeFactory = null;

        String expected = "Actuator Type Factory cannot be null";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorTypeDataModelAssembler(actuatorTypeFactory));

        // Assert
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    /**
     * Test to ensure that an ActuatorTypeDataModelAssembler can be instantiated successfully.
     */
    @Test
    void shouldInstantiateActuatorTypeDataModelAssembler_whenActuatorTypeFactoryIsValid() {
        // Arrange
        IActuatorTypeFactory actuatorTypeFactory = mock(IActuatorTypeFactory.class);

        // Act
        ActuatorTypeDataModelAssembler actuatorTypeDataModelAssembler = new ActuatorTypeDataModelAssembler(actuatorTypeFactory);

        // Assert
        assertNotNull(actuatorTypeDataModelAssembler);
    }

    /**
     * Test to ensure that an ActuatorType can be converted to a DataModel successfully.
     */
    @Test
    void shouldConvertActuatorTypeDataModelToDomain_WhenActuatorTypeDataModelIsValid() {
        // Arrange
        String actuatorTypeID = "1";
        String actuatorTypeName = "Temperature";
        String unitID = "Celsius";

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        TypeDescription actuatorTypeNameDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);

        ActuatorTypeDataModel actuatorTypeDataModelDouble = mock(ActuatorTypeDataModel.class);

        when(actuatorTypeDataModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
        when(actuatorTypeDataModelDouble.getActuatorTypeName()).thenReturn(actuatorTypeName);
        when(actuatorTypeDataModelDouble.getUnitID()).thenReturn(unitID);

        IActuatorTypeFactory actuatorTypeFactory = mock(IActuatorTypeFactory.class);
        ActuatorTypeDataModelAssembler actuatorTypeDataModelAssembler = new ActuatorTypeDataModelAssembler(actuatorTypeFactory);

        ActuatorType expected = actuatorTypeFactory.createActuatorType(actuatorTypeNameDouble, unitIDDouble, actuatorTypeIDDouble);

        // Act
        ActuatorType result = actuatorTypeDataModelAssembler.toDomain(actuatorTypeDataModelDouble);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that a list of ActuatorTypeDataModels can be converted to a list of ActuatorTypes successfully.
     */
    @Test
    void shouldConvertActuatorTypeDataModelListToDomainList_WhenActuatorTypeDataModelListIsValid() {
        // Arrange
        String actuatorTypeID = "1";
        String actuatorTypeName = "Temperature";
        String unitID = "Celsius";

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        TypeDescription actuatorTypeNameDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);

        ActuatorTypeDataModel actuatorTypeDataModelDouble = mock(ActuatorTypeDataModel.class);

        when(actuatorTypeDataModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
        when(actuatorTypeDataModelDouble.getActuatorTypeName()).thenReturn(actuatorTypeName);
        when(actuatorTypeDataModelDouble.getUnitID()).thenReturn(unitID);

        IActuatorTypeFactory actuatorTypeFactory = mock(IActuatorTypeFactory.class);
        ActuatorTypeDataModelAssembler actuatorTypeDataModelAssembler = new ActuatorTypeDataModelAssembler(actuatorTypeFactory);

        ActuatorType expected = actuatorTypeFactory.createActuatorType(actuatorTypeNameDouble, unitIDDouble, actuatorTypeIDDouble);

        // Act
        ActuatorType result = actuatorTypeDataModelAssembler.toDomain(actuatorTypeDataModelDouble);

        // Assert
        assertEquals(expected, result);
    }
}