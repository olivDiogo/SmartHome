package smart_home.persistence.assembler;

import org.junit.jupiter.api.Test;
import smart_home.domain.unit.Unit;
import smart_home.domain.unit.UnitFactoryImpl;
import smart_home.persistence.jpa.data_model.UnitDataModel;
import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitID;
import smart_home.value_object.UnitSymbol;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UnitDataModelAssemblerTest {

    @Test
    void shouldInstantiateUnitDataModelAssembler_whenUnitFactoryIsValid(){

        // Arrange
        UnitFactoryImpl unitFactory = mock(UnitFactoryImpl.class);

        // Act
        UnitDataModelAssembler unitDataModelAssembler = new UnitDataModelAssembler(unitFactory);

        // Assert
        assertNotNull(unitDataModelAssembler);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenUnitFactoryIsNull(){

            // Arrange
            UnitFactoryImpl unitFactory = null;
            String expectedMessage = "UnitFactory cannot be null.";

            // Act
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitDataModelAssembler(unitFactory));

            // Assert
            String actualMessage = exception.getMessage();
            assertNotNull(expectedMessage, actualMessage);
    }

    @Test
    void shouldReturnUnit_whenGivenValidUnitDataModel(){

        // Arrange
        UnitFactoryImpl unitFactoryDouble = mock(UnitFactoryImpl.class);
        UnitDataModelAssembler unitDataModelAssembler = new UnitDataModelAssembler(unitFactoryDouble);
        UnitDataModel unitDataModelDouble = mock(UnitDataModel.class);
        when(unitDataModelDouble.getUnitID()).thenReturn("1L");
        when(unitDataModelDouble.getUnitSymbol()).thenReturn("m");
        when(unitDataModelDouble.getUnitDescription()).thenReturn("meter");

        UnitID unitIDDouble = mock(UnitID.class);
        when(unitIDDouble.getID()).thenReturn("1L");

        UnitSymbol unitSymbolDouble = mock(UnitSymbol.class);
        when(unitSymbolDouble.getSymbol()).thenReturn("m");

        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        when(unitDescriptionDouble.getDescription()).thenReturn("meter");

        Unit expected = unitFactoryDouble.createUnit(unitDescriptionDouble, unitSymbolDouble);

        // Act
        Unit unit = unitDataModelAssembler.toDomain(unitDataModelDouble);

        // Assert
        assertEquals(expected, unit);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullUnitDataModel(){

        // Arrange
        UnitFactoryImpl unitFactoryDouble = mock(UnitFactoryImpl.class);
        UnitDataModelAssembler unitDataModelAssembler = new UnitDataModelAssembler(unitFactoryDouble);
        UnitDataModel unitDataModelDouble = null;
        String expectedMessage = "UnitDataModel cannot be null.";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> unitDataModelAssembler.toDomain(unitDataModelDouble));

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldReturnListOfUnits_whenGivenListOfUnitDataModels(){

        // Arrange
        UnitFactoryImpl unitFactoryDouble = mock(UnitFactoryImpl.class);
        when(unitFactoryDouble.createUnit(any(UnitDescription.class), any(UnitSymbol.class))).thenReturn(mock(Unit.class));

        UnitDataModelAssembler unitDataModelAssembler = new UnitDataModelAssembler(unitFactoryDouble);

        UnitID unitIDDouble = mock(UnitID.class);
        when(unitIDDouble.getID()).thenReturn("1L");

        UnitSymbol unitSymbolDouble = mock(UnitSymbol.class);
        when(unitSymbolDouble.getSymbol()).thenReturn("m");

        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        when(unitDescriptionDouble.getDescription()).thenReturn("meter");

        UnitDataModel unitDataModelDouble = mock(UnitDataModel.class);
        when(unitDataModelDouble.getUnitID()).thenReturn("1L");
        when(unitDataModelDouble.getUnitSymbol()).thenReturn("m");
        when(unitDataModelDouble.getUnitDescription()).thenReturn("meter");

        List<UnitDataModel> unitDataModels = List.of(unitDataModelDouble);

        Unit expected = unitFactoryDouble.createUnit(unitDescriptionDouble, unitSymbolDouble);

        // Act
        List<Unit> units = unitDataModelAssembler.toDomain(unitDataModels);

        // Assert
        assertEquals(expected, units.get(0));
    }

}