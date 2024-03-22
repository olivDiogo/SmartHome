package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.ActuatorTypeDTO;
import SmartHomeDDD.ValueObject.TypeDescription;
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
        String descriptionID = "Switch Actuator";

        ActuatorType actuatorType = mock(ActuatorType.class);

        when(actuatorType.getID()).thenReturn(mock(TypeDescription.class));
        when(actuatorType.getID().toString()).thenReturn(descriptionID);

        ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

        ActuatorTypeDTO expectedActuatorType = new ActuatorTypeDTO(descriptionID);

        //Act
        ActuatorTypeDTO actuatorTypeDTO = actuatorTypeAssembler.domainToDTO(actuatorType);

        //assert
        assertEquals(expectedActuatorType.actuatorTypeDescription, actuatorTypeDTO.actuatorTypeDescription);
    }

    /**
     * Test if the domainToDTO method returns a list of ActuatorTypeDTO objects when the description type is valid.
     */
    @Test
    public void shouldReturnActuatorTypeAssemblerDTOList_WhenDescriptionTypeIsValid() {
        //Arrange
        String actuatorTypeDescription1 = "Blind Actuator";
        String actuatorTypeDescription2 = "Switch Actuator";

        ActuatorType actuatorType = mock(ActuatorType.class);
        ActuatorType actuatorType2 = mock(ActuatorType.class);

        when(actuatorType.getID()).thenReturn(mock(TypeDescription.class));
        when(actuatorType.getID().toString()).thenReturn(actuatorTypeDescription1);

        when(actuatorType2.getID()).thenReturn(mock(TypeDescription.class));
        when(actuatorType2.getID().toString()).thenReturn(actuatorTypeDescription2);

        List<ActuatorType> actuatorTypeList = new ArrayList<>();
        actuatorTypeList.add(actuatorType);
        actuatorTypeList.add(actuatorType2);

        ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeDescription1);
        ActuatorTypeDTO actuatorTypeDTO1 = new ActuatorTypeDTO(actuatorTypeDescription2);

        List<ActuatorTypeDTO> expected = new ArrayList<>();
        expected.add(actuatorTypeDTO);
        expected.add(actuatorTypeDTO1);

        //Act
        List<ActuatorTypeDTO> result = actuatorTypeAssembler.domainToDTO(actuatorTypeList);

        //Assert
        assertEquals(expected.toString(), result.toString());
        assertEquals(expected.get(0).actuatorTypeDescription, result.get(0).actuatorTypeDescription);
        assertEquals(expected.get(1).actuatorTypeDescription, result.get(1).actuatorTypeDescription);
    }
}

