package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.DeviceTypeDTO;
import SmartHomeDDD.domain.DeviceType.DeviceType;
import SmartHomeDDD.valueObject.DeviceTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for DeviceTypeAssembler.
 */
class DeviceTypeAssemblerTest {
    @Test
    public void shouldInstantiateDeviceTypeAssembler() {
        new DeviceTypeAssembler();
    }

    @Test
    public void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeIsValid() {
        //Arrange
        String deviceTypeID = "Switch Device";
        String deviceTypeDescription = "Switch Device Description";

        DeviceType deviceType = mock(DeviceType.class);
        when(deviceType.getID()).thenReturn(mock(DeviceTypeID.class));
        when(deviceType.getID().toString()).thenReturn(deviceTypeID);
        when(deviceType.getDescription()).thenReturn(mock(TypeDescription.class));
        when(deviceType.getDescription().toString()).thenReturn(deviceTypeDescription);

        DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
        DeviceTypeDTO expectedDeviceType = new DeviceTypeDTO(deviceTypeID, deviceTypeDescription);
        //Act
        DeviceTypeDTO deviceTypeDTO = deviceTypeAssembler.domainToDTO(deviceType);
        //Assert
        assertEquals(expectedDeviceType.deviceTypeID, deviceTypeDTO.deviceTypeID);

    }

    @Test
    public void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeIsNull() {
        // Arrange
        String DeviceTypeID = "Switch Device";
        DeviceType deviceType = mock(DeviceType.class); // Criação do mock do objeto DeviceType
        DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            deviceTypeAssembler.domainToDTO((DeviceType) null);
        });
        // Assert
        assertEquals("The DeviceType cannot be null.", exception.getMessage());
    }

    @Test
    public void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeListIsNull() {
        // Arrange
        List<DeviceType> deviceTypes = null;
        DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            deviceTypeAssembler.domainToDTO(deviceTypes);
        });
        // Assert
        assertEquals("The list of DeviceTypes cannot be null, empty, or contain null elements.", exception.getMessage());
    }

    @Test
    public void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeListIsEmpty() {
        // Arrange
        List<DeviceType> deviceTypes = List.of();
        DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            deviceTypeAssembler.domainToDTO(deviceTypes);
        });
        // Assert
        assertEquals("The list of DeviceTypes cannot be null, empty, or contain null elements.", exception.getMessage());
    }

    @Test
    public void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeListIsValid() {
        // Arrange
        String deviceTypeID = "Switch Device";
        String deviceTypeDescription = "Switch Device Description";

        DeviceType deviceType = mock(DeviceType.class);
        when(deviceType.getID()).thenReturn(mock(DeviceTypeID.class));
        when(deviceType.getID().toString()).thenReturn(deviceTypeID);
        when(deviceType.getDescription()).thenReturn(mock(TypeDescription.class));
        when(deviceType.getDescription().toString()).thenReturn(deviceTypeDescription);

        DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
        List<DeviceType> deviceTypes = Arrays.asList(deviceType, deviceType);
        DeviceTypeDTO expectedDeviceType = new DeviceTypeDTO(deviceTypeID, deviceTypeDescription);
        // Act
        List<DeviceTypeDTO> deviceTypeDTOS = deviceTypeAssembler.domainToDTO(deviceTypes);
        // Assert
        assertEquals(2, deviceTypeDTOS.size());
        assertEquals(expectedDeviceType.deviceTypeID, deviceTypeDTOS.get(0).deviceTypeID);
        assertEquals(expectedDeviceType.deviceTypeID, deviceTypeDTOS.get(1).deviceTypeID);
    }

    @Test
    public void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeIDIsValid() {
        // Arrange
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
        when(deviceTypeID.toString()).thenReturn("Switch Device");
        DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
        // Act
        String deviceTypeIDString = deviceTypeAssembler.deviceTypeIDToString(deviceTypeID);
        // Assert
        assertEquals("Switch Device", deviceTypeIDString);
    }

    @Test
    public void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeDescriptionIsValid() {
        // Arrange
        TypeDescription deviceTypeDescription = mock(TypeDescription.class);
        when(deviceTypeDescription.toString()).thenReturn("Switch Device Description");
        DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
        // Act
        String deviceTypeDescriptionString = deviceTypeAssembler.deviceTypeDescriptionToString(deviceTypeDescription);
        // Assert
        assertEquals("Switch Device Description", deviceTypeDescriptionString);
    }

    @Test
    public void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeDescriptionIsNull() {
        // Arrange
        TypeDescription deviceTypeDescription = null;
        DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            deviceTypeAssembler.deviceTypeDescriptionToString(deviceTypeDescription);
        });
    }

    @Test
    public void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeIDIsNull() {
        // Arrange
        DeviceTypeID deviceTypeID = null;
        DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            deviceTypeAssembler.deviceTypeIDToString(deviceTypeID);
        });
    }

}
