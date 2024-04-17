package smart_home.persistence.jpa.data_model;

import org.junit.jupiter.api.Test;
import smart_home.domain.unit.Unit;
import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitID;
import smart_home.value_object.UnitSymbol;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UnitDataModelTest {

    @Test
    void shouldInstantiateUnitDataModel_whenEmptyConstructorIsCalled(){
        // Arrange
        UnitDataModel unitDataModel = new UnitDataModel();

        // Act
        assertNotNull(unitDataModel);
    }

    @Test
    void shouldInstantiateUnitDataModel_whenUnitIsValid(){
        // Arrange
        Unit unit = mock(Unit.class);
        when(unit.getID()).thenReturn(mock(UnitID.class));
        when(unit.getUnitSymbol()).thenReturn(mock(UnitSymbol.class));
        when(unit.getUnitDescription()).thenReturn(mock(UnitDescription.class));

        // Act
        UnitDataModel unitDataModel = new UnitDataModel(unit);

        // Assert
        assertNotNull(unitDataModel);
    }

    @Test
    void shouldThrowException_whenUnitIsNull(){
        // Arrange
        Unit unit = null;

        String expectedMessage = "Unit cannot be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new UnitDataModel(unit));

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldReturnUnitID_whenGetUnitIDIsCalled(){
        // Arrange
        Unit unit = mock(Unit.class);
        UnitID unitID = mock(UnitID.class);
        when(unitID.getID()).thenReturn("1L");
        when(unit.getID()).thenReturn(unitID);
        when(unit.getUnitDescription()).thenReturn(mock(UnitDescription.class));
        when(unit.getUnitSymbol()).thenReturn(mock(UnitSymbol.class));

        UnitDataModel unitDataModel = new UnitDataModel(unit);

        // Act
        String ActualUnitID = unitDataModel.getUnitID();

        // Assert
        assertEquals(unitID.getID(), ActualUnitID);
    }

    @Test
    void shouldReturnUnitSymbol_whenGetUnitSymbolIsCalled(){
        // Arrange
        Unit unit = mock(Unit.class);
        UnitSymbol unitSymbolDouble = mock(UnitSymbol.class);
        when(unitSymbolDouble.getUnit()).thenReturn("UnitSymbol");
        when(unit.getUnitSymbol()).thenReturn(unitSymbolDouble);
        when(unit.getUnitDescription()).thenReturn(mock(UnitDescription.class));
        when(unit.getID()).thenReturn(mock(UnitID.class));

        UnitDataModel unitDataModel = new UnitDataModel(unit);

        // Act
        String unitSymbol = unitDataModel.getUnitSymbol();

        // Assert
        assertEquals("UnitSymbol", unitSymbol);
    }

    @Test
    void shouldReturnUnitDescription_whenGetUnitDescriptionIsCalled(){
        // Arrange
        Unit unit = mock(Unit.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        when(unitDescriptionDouble.getDescription()).thenReturn("UnitDescription");
        when(unit.getUnitDescription()).thenReturn(unitDescriptionDouble);
        when(unit.getUnitSymbol()).thenReturn(mock(UnitSymbol.class));
        when(unit.getID()).thenReturn(mock(UnitID.class));

        UnitDataModel unitDataModel = new UnitDataModel(unit);

        // Act
        String unitDescription = unitDataModel.getUnitDescription();

        // Assert
        assertEquals("UnitDescription", unitDescription);
    }

}