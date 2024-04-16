package smart_home.persistence.assembler;

import org.junit.jupiter.api.Test;
import smart_home.domain.sensor_type.ISensorTypeFactory;
import smart_home.domain.sensor_type.SensorType;
import smart_home.domain.sensor_type.SensorTypeFactoryImpl;
import smart_home.persistence.jpa.data_model.SensorTypeDataModel;
import smart_home.value_object.SensorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorTypeDataModelAssemblerTest {

    @Test
    void shouldThrowException_whenSensorTypeFactoryIsNull() {
        // Arrange
        ISensorTypeFactory sensorTypeFactory = null;

        String expected = "Sensor Type Factory cannot be null";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorTypeDataModelAssembler(sensorTypeFactory));

        // Assert
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void shouldInstantiateSensorTypeDataModelConverter_whenSensorTypeFactoryIsValid() {
        // Arrange
        ISensorTypeFactory sensorTypeFactory = mock(ISensorTypeFactory.class);

        // Act
        SensorTypeDataModelAssembler sensorTypeDataModelConverter = new SensorTypeDataModelAssembler(sensorTypeFactory);

        // Assert
        assertNotNull(sensorTypeDataModelConverter);
    }

    @Test
    void shouldConvertSensorTypeDataModelToDomain_WhenSensorTypeDataModelIsValid () {
        //Arrange
        String sensorTypeID = "1";
        String sensorTypeDescription = "Temperature";
        String unit = "Celsius";

        SensorTypeID sensorTypeIdDouble = mock(SensorTypeID.class);
        TypeDescription sensorTypeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);

        SensorTypeDataModel sensorTypeDataModelDouble = mock(SensorTypeDataModel.class);
        when(sensorTypeDataModelDouble.getSensorTypeID()).thenReturn(sensorTypeID);
        when(sensorTypeDataModelDouble.getTypeDescription()).thenReturn(sensorTypeDescription);
        when(sensorTypeDataModelDouble.getUnitID()).thenReturn(unit);

        ISensorTypeFactory sensorTypeFactory = mock(ISensorTypeFactory.class);
        SensorTypeDataModelAssembler sensorTypeDataModelConverter = new SensorTypeDataModelAssembler(sensorTypeFactory);

        SensorType expected = sensorTypeFactory.createSensorType(sensorTypeIdDouble, sensorTypeDescriptionDouble, unitDouble);

        //Act
        SensorType result = sensorTypeDataModelConverter.toDomain(sensorTypeDataModelDouble);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void shouldThrowException_WhenSensorDataModelIsNull () {
        //Arrange
        SensorTypeDataModel sensorTypeDataModel = null;

        ISensorTypeFactory sensorTypeFactory = mock(ISensorTypeFactory.class);
        SensorTypeDataModelAssembler sensorTypeDataModelConverter = new SensorTypeDataModelAssembler(sensorTypeFactory);

        String expected = "Sensor Type Data Model cannot be null";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensorTypeDataModelConverter.toDomain(sensorTypeDataModel));

        //Assert
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void shouldConvertSensorTypeDataModelListToDomainList_WhenSensorTypeDataModelListIsValid () {
        //Arrange
        String sensorTypeID = "1";
        String sensorTypeDescription = "Temperature";
        String unit = "Celsius";

        String sensorTypeID2 = "2";
        String sensorTypeDescription2 = "Temperature";
        String unit2 = "Celsius";

        /* SensorDataModel 1 */
        SensorTypeID sensorTypeIdDouble = mock(SensorTypeID.class);
        when(sensorTypeIdDouble.toString()).thenReturn(sensorTypeID);

        TypeDescription sensorTypeDescriptionDouble = mock(TypeDescription.class);
        when(sensorTypeDescriptionDouble.toString()).thenReturn(sensorTypeDescription);

        UnitID unitDouble = mock(UnitID.class);
        when(unitDouble.toString()).thenReturn(unit);

        SensorTypeDataModel sensorTypeDataModelDouble = mock(SensorTypeDataModel.class);
        when(sensorTypeDataModelDouble.getSensorTypeID()).thenReturn(sensorTypeID);
        when(sensorTypeDataModelDouble.getTypeDescription()).thenReturn(sensorTypeDescription);
        when(sensorTypeDataModelDouble.getUnitID()).thenReturn(unit);

        /* SensorDataModel 2 */
        SensorTypeID sensorTypeIdDouble2 = mock(SensorTypeID.class);
        when(sensorTypeIdDouble2.toString()).thenReturn(sensorTypeID2);

        TypeDescription sensorTypeDescriptionDouble2 = mock(TypeDescription.class);
        when(sensorTypeDescriptionDouble2.toString()).thenReturn(sensorTypeDescription2);

        UnitID unitDouble2 = mock(UnitID.class);
        when(unitDouble2.toString()).thenReturn(unit2);

        SensorTypeDataModel sensorTypeDataModelDouble2 = mock(SensorTypeDataModel.class);
        when(sensorTypeDataModelDouble2.getSensorTypeID()).thenReturn(sensorTypeID2);
        when(sensorTypeDataModelDouble2.getTypeDescription()).thenReturn(sensorTypeDescription2);
        when(sensorTypeDataModelDouble2.getUnitID()).thenReturn(unit2);

        SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);

        SensorTypeDataModelAssembler sensorTypeDataModelConverter = new SensorTypeDataModelAssembler(sensorTypeFactory);

        List<SensorTypeDataModel> sensorTypeDataModelList = new ArrayList<>();
        sensorTypeDataModelList.add(sensorTypeDataModelDouble);
        sensorTypeDataModelList.add(sensorTypeDataModelDouble2);

        /*Expected SensorType  */
        SensorType expSensorType1 = mock(SensorType.class);
        SensorType expSensorType2 = mock(SensorType.class);

        when(sensorTypeFactory.createSensorType(any(SensorTypeID.class), any(TypeDescription.class), any(UnitID.class))).thenReturn(expSensorType1, expSensorType2);


        List<SensorType> expectedList = List.of(expSensorType1, expSensorType2);

        //Act
        List<SensorType> result = sensorTypeDataModelConverter.toDomain(sensorTypeDataModelList);

        //Assert
        assertEquals(expectedList, result);
    }

    @Test
    void shouldThrowException_WhenListOfSensorTypeDataModelsIsNull () {
        //Arrange
        List<SensorTypeDataModel> sensorTypeDataModelList = null;

        ISensorTypeFactory sensorTypeFactory = mock(ISensorTypeFactory.class);
        SensorTypeDataModelAssembler sensorTypeDataModelConverter = new SensorTypeDataModelAssembler(sensorTypeFactory);

        String expected = "The list of sensor types cannot be null or empty.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensorTypeDataModelConverter.toDomain(sensorTypeDataModelList));

        //Assert
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowException_WhenListOfSensorTypeDataModelsIsEmpty () {
        //Arrange
        List<SensorTypeDataModel> sensorTypeDataModelList = new ArrayList<>();

        ISensorTypeFactory sensorTypeFactory = mock(ISensorTypeFactory.class);
        SensorTypeDataModelAssembler sensorTypeDataModelConverter = new SensorTypeDataModelAssembler(sensorTypeFactory);

        String expected = "The list of sensor types cannot be null or empty.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensorTypeDataModelConverter.toDomain(sensorTypeDataModelList));

        //Assert
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }


}