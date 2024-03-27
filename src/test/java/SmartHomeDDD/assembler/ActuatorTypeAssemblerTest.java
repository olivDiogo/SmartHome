package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.ActuatorTypeDTO;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActuatorTypeAssemblerTest {

    /**
     * Test if the constructor of the ActuatorTypeAssembler class can be called.
     */
    @Test
    public void shouldInstantiateActuatorTypeAssembler() {
        new ActuatorTypeAssembler();
    }

    /**
     * Test if the domainToDTO method returns an ActuatorTypeDTO object when the description type is valid.
     */
    @Test
    public void shouldReturnActuatorTypeAssemblerDTO_WhenDescriptionTypeIsValid() {
        //Arrange
        String actuatorTypeID = "Switch Actuator";

        ActuatorType actuatorType = mock(ActuatorType.class);

        when(actuatorType.getID()).thenReturn(mock(ActuatorTypeID.class));
        when(actuatorType.getID().toString()).thenReturn(actuatorTypeID);

        ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

        ActuatorTypeDTO expectedActuatorType = new ActuatorTypeDTO(actuatorTypeID);

        //Act
        ActuatorTypeDTO actuatorTypeDTO = actuatorTypeAssembler.domainToDTO(actuatorType);

        //assert
        assertEquals(expectedActuatorType.actuatorTypeID, actuatorTypeDTO.actuatorTypeID);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the ActuatorType is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenActuatorTypeIsNull() {
        //Arrange
        ActuatorType actuatorType = null;
        ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

        String expected = "The ActuatorType cannot be null.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> actuatorTypeAssembler.domainToDTO(actuatorType));

        //Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test if the domainToDTO method returns a list of ActuatorTypeDTO objects when the description type is valid.
     */
    @Test
    public void shouldReturnActuatorTypeAssemblerDTOList_WhenDescriptionTypeIsValid() {
        //Arrange
        String actuatorTypeID1 = "BlindActuator";
        String actuatorTypeID2 = "SwitchActuator";

        ActuatorType actuatorType = mock(ActuatorType.class);
        ActuatorType actuatorType2 = mock(ActuatorType.class);

        when(actuatorType.getID()).thenReturn(mock(ActuatorTypeID.class));
        when(actuatorType.getID().toString()).thenReturn(actuatorTypeID1);

        when(actuatorType2.getID()).thenReturn(mock(ActuatorTypeID.class));
        when(actuatorType2.getID().toString()).thenReturn(actuatorTypeID2);

        List<ActuatorType> actuatorTypeList = new ArrayList<>();
        actuatorTypeList.add(actuatorType);
        actuatorTypeList.add(actuatorType2);

        ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID1);
        ActuatorTypeDTO actuatorTypeDTO1 = new ActuatorTypeDTO(actuatorTypeID2);

        List<ActuatorTypeDTO> expected = new ArrayList<>();
        expected.add(actuatorTypeDTO);
        expected.add(actuatorTypeDTO1);

        //Act
        List<ActuatorTypeDTO> result = actuatorTypeAssembler.domainToDTO(actuatorTypeList);

        //Assert
        assertEquals(expected.toString(), result.toString());
        assertEquals(expected.get(0).actuatorTypeID, result.get(0).actuatorTypeID);
        assertEquals(expected.get(1).actuatorTypeID, result.get(1).actuatorTypeID);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of ActuatorTypes is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenActuatorTypeListIsNull() {
        //Arrange
        List<ActuatorType> actuatorTypeList = null;
        ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

        String expected = "The list of ActuatorTypes cannot be null.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> actuatorTypeAssembler.domainToDTO(actuatorTypeList));

        //Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    @Test
    public void shouldThrowIllegalArgumentException_WhenActuatorTypeListIsEmpty() {
        //Arrange
        List<ActuatorType> actuatorTypeList = new ArrayList<>();
        ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

        String expected = "The list of ActuatorTypes cannot be null.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> actuatorTypeAssembler.domainToDTO(actuatorTypeList));

        //Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of ActuatorTypes contains null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenActuatorTypeListContainsNull() {
        //Arrange
        ActuatorType actuatorType = mock(ActuatorType.class);

        String actuatorTypeID = "Blind Actuator";
        when(actuatorType.getID()).thenReturn(mock(ActuatorTypeID.class));
        when(actuatorType.getID().toString()).thenReturn(actuatorTypeID);

        List<ActuatorType> actuatorTypeList = new ArrayList<>();
        actuatorTypeList.add(actuatorType);
        actuatorTypeList.add(null);

        ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

        String expected = "The list of ActuatorTypes cannot be null.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> actuatorTypeAssembler.domainToDTO(actuatorTypeList));

        //Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }
}

