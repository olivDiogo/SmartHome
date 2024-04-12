package smartHome.assembler;

import smartHome.dto.ActuatorModelDTO;
import smartHome.domain.actuatorModel.ActuatorModel;
import smartHome.valueObject.ActuatorModelName;
import smartHome.valueObject.ModelPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorModelAssemblerTest {

    /**
     * Tests the conversion of an actuator model to an actuator model DTO, when the actuator model is valid.
     */
    @Test
    void shouldConvertActuatorModelToActuatorModelDTO_whenActuatorModelIsValid() {
        // Arrange
        String actuatorModelID = "path";
        ModelPath actuatorModelIDDouble = mock(ModelPath.class);
        when(actuatorModelIDDouble.toString()).thenReturn(actuatorModelID);

        String actuatorModelName = "Light";
        ActuatorModelName actuatorModelNameDouble = mock(ActuatorModelName.class);
        when(actuatorModelNameDouble.toString()).thenReturn(actuatorModelName);

        String actuatorModelPath = "path";
        ModelPath actuatorModelPathDouble = mock(ModelPath.class);
        when(actuatorModelPathDouble.toString()).thenReturn(actuatorModelPath);

        ActuatorModel actuatorModelDouble = mock(ActuatorModel.class);
        when(actuatorModelDouble.getID()).thenReturn(actuatorModelIDDouble);
        when(actuatorModelDouble.getActuatorModelName()).thenReturn(actuatorModelNameDouble);
        when(actuatorModelDouble.getID()).thenReturn(actuatorModelPathDouble);

        ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();
        String expected = actuatorModelID + " " + actuatorModelName + " " + actuatorModelPath;

        // Act
        ActuatorModelDTO actuatorModelDTO = actuatorModelAssembler.domainToDTO(actuatorModelDouble);

        // Assert
        assertEquals(expected, actuatorModelDTO.toString());
    }

    /**
     * Tests the conversion of an actuator model to an actuator model DTO, when the actuator model is null.
     */
    @Test
    void shouldThrowException_whenActuatorModelIsNull() {
        // Arrange
        ActuatorModel actuatorModel = null;
        ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> actuatorModelAssembler.domainToDTO(actuatorModel));
    }

    /**
     * Tests the conversion of a list of actuator models to a list of actuator model DTOs, when the list of actuator models is valid.
     */
    @Test
    void shouldConvertListOfActuatorModelsToListOfActuatorModelDTO_whenActuatorModelListIsValid(){
        // Arrange
        /* ActuatorModel 1 */
        String actuatorModelID1 = "path1";
        ModelPath actuatorModelIDDouble1 = mock(ModelPath.class);
        when(actuatorModelIDDouble1.toString()).thenReturn(actuatorModelID1);

        String actuatorModelName1 = "Light";
        ActuatorModelName actuatorModelNameDouble1 = mock(ActuatorModelName.class);
        when(actuatorModelNameDouble1.toString()).thenReturn(actuatorModelName1);

        String actuatorModelPath1 = "path1";
        ModelPath actuatorModelPathDouble1 = mock(ModelPath.class);
        when(actuatorModelPathDouble1.toString()).thenReturn(actuatorModelPath1);

        ActuatorModel actuatorModelDouble1 = mock(ActuatorModel.class);
        when(actuatorModelDouble1.getID()).thenReturn(actuatorModelIDDouble1);
        when(actuatorModelDouble1.getActuatorModelName()).thenReturn(actuatorModelNameDouble1);
        when(actuatorModelDouble1.getID()).thenReturn(actuatorModelPathDouble1);

        /* ActuatorModel 2 */
        String actuatorModelID2 = "path2";
        ModelPath actuatorModelIDDouble2 = mock(ModelPath.class);
        when(actuatorModelIDDouble2.toString()).thenReturn(actuatorModelID2);

        String actuatorModelName2 = "Fan";
        ActuatorModelName actuatorModelNameDouble2 = mock(ActuatorModelName.class);
        when(actuatorModelNameDouble2.toString()).thenReturn(actuatorModelName2);

        String actuatorModelPath2 = "path2";
        ModelPath actuatorModelPathDouble2 = mock(ModelPath.class);
        when(actuatorModelPathDouble2.toString()).thenReturn(actuatorModelPath2);

        ActuatorModel actuatorModelDouble2 = mock(ActuatorModel.class);
        when(actuatorModelDouble2.getID()).thenReturn(actuatorModelIDDouble2);
        when(actuatorModelDouble2.getActuatorModelName()).thenReturn(actuatorModelNameDouble2);
        when(actuatorModelDouble2.getID()).thenReturn(actuatorModelPathDouble2);

        List<ActuatorModel> actuatorModels = List.of(actuatorModelDouble1, actuatorModelDouble2);

        ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();
        ActuatorModelDTO actuatorModelDTO1 = new ActuatorModelDTO(actuatorModelID1, actuatorModelName1, actuatorModelPath1);
        ActuatorModelDTO actuatorModelDTO2 = new ActuatorModelDTO(actuatorModelID2, actuatorModelName2, actuatorModelPath2);
        List<ActuatorModelDTO> expected = List.of(actuatorModelDTO1, actuatorModelDTO2);

        // Act
        List<ActuatorModelDTO> actuatorModelsDTO = actuatorModelAssembler.domainToDTO(actuatorModels);

        // Assert
        assertEquals(expected.toString(), actuatorModelsDTO.toString());
    }

    /**
     * Tests the conversion of a list of actuator models to a list of actuator model DTOs when the list is null.
     */
    @Test
    void shouldThrowException_whenActuatorModelListIsNull() {
        // Arrange
        List<ActuatorModel> actuatorModels = null;
        ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> actuatorModelAssembler.domainToDTO(actuatorModels));
    }

    /**
     * Tests the conversion of a list of actuator models to a list of actuator model DTOs when the list is empty.
     */
    @Test
    void shouldThrowException_whenActuatorModelListIsEmpty() {
        // Arrange
        List<ActuatorModel> actuatorModels = List.of();
        ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> actuatorModelAssembler.domainToDTO(actuatorModels));
    }
}
