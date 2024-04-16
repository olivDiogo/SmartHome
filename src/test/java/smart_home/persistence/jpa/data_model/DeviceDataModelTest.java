package smart_home.persistence.jpa.data_model;

import org.junit.jupiter.api.Test;
import smart_home.domain.device.Device;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceDataModelTest {

    /**
     * Test to check if the DeviceDataModel is instantiated
     */
    @Test
    void shouldInstantiateDeviceDataModel_WhenAsNoArguments() {
        // Act
        DeviceDataModel deviceModel = new DeviceDataModel();
        // Assert
        assertNotNull(deviceModel);
    }


    /**
     * Test constructor with null parameter.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenDeviceIsNull(){
        //Arrange
        Device device = null;
        String expectedMessage = "Device cannot be null.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DeviceDataModel(device));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Test constructor with valid parameter.
     */
    @Test
    void shouldCreateDeviceDataModel_WhenDeviceIsNotNull(){
        //Arrange
        String strRoomID = "1";
        String strDeviceName = "Light";
        String strDeviceTypeID = "1";
        boolean deviceStatus = true;
        String strDeviceID = "1";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        RoomID roomIDDouble = mock(RoomID.class);
        DeviceName deviceNameDouble = mock(DeviceName.class);
        DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
        DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

        when(deviceIDDouble.getID()).thenReturn(strDeviceID);
        when(roomIDDouble.getID()).thenReturn(strRoomID);
        when(deviceNameDouble.getName()).thenReturn(strDeviceName);
        when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
        when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

        Device deviceDouble = mock(Device.class);

        when(deviceDouble.getID()).thenReturn(deviceIDDouble);
        when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
        when(deviceDouble.getDeviceName()).thenReturn(deviceNameDouble);
        when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
        when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

        //Act
        DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

        //Assert
        assertNotNull(deviceDataModel);
    }

    /**
     * Test getDeviceID method.
     */
    @Test
    void shouldReturnDeviceID_WhenGetDeviceID(){
        //Arrange
        String strRoomID = "1";
        String strDeviceName = "Light";
        String strDeviceTypeID = "1";
        boolean deviceStatus = true;
        String strDeviceID = "1";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        RoomID roomIDDouble = mock(RoomID.class);
        DeviceName deviceNameDouble = mock(DeviceName.class);
        DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
        DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

        when(deviceIDDouble.getID()).thenReturn(strDeviceID);
        when(roomIDDouble.getID()).thenReturn(strRoomID);
        when(deviceNameDouble.getName()).thenReturn(strDeviceName);
        when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
        when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

        Device deviceDouble = mock(Device.class);

        when(deviceDouble.getID()).thenReturn(deviceIDDouble);
        when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
        when(deviceDouble.getDeviceName()).thenReturn(deviceNameDouble);
        when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
        when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

        DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

        //Act
        String result = deviceDataModel.getDeviceID();

        //Assert
        assertEquals(strDeviceID, result);
    }

    /**
     * Test getRoomID method.
     */
    @Test
    void shouldReturnRoomID_WhenGetRoomID(){
        //Arrange
        String strRoomID = "1";
        String strDeviceName = "Light";
        String strDeviceTypeID = "1";
        boolean deviceStatus = true;
        String strDeviceID = "1";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        RoomID roomIDDouble = mock(RoomID.class);
        DeviceName deviceNameDouble = mock(DeviceName.class);
        DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
        DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

        when(deviceIDDouble.getID()).thenReturn(strDeviceID);
        when(roomIDDouble.getID()).thenReturn(strRoomID);
        when(deviceNameDouble.getName()).thenReturn(strDeviceName);
        when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
        when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

        Device deviceDouble = mock(Device.class);

        when(deviceDouble.getID()).thenReturn(deviceIDDouble);
        when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
        when(deviceDouble.getDeviceName()).thenReturn(deviceNameDouble);
        when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
        when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

        DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

        //Act
        String result = deviceDataModel.getRoomID();

        //Assert
        assertEquals(strRoomID, result);
    }

    /**
     * Test getDeviceName method.
     */
    @Test
    void shouldReturnDeviceName_WhenGetDeviceName(){
        //Arrange
        String strRoomID = "1";
        String strDeviceName = "Light";
        String strDeviceTypeID = "1";
        boolean deviceStatus = true;
        String strDeviceID = "1";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        RoomID roomIDDouble = mock(RoomID.class);
        DeviceName deviceNameDouble = mock(DeviceName.class);
        DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
        DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

        when(deviceIDDouble.getID()).thenReturn(strDeviceID);
        when(roomIDDouble.getID()).thenReturn(strRoomID);
        when(deviceNameDouble.getName()).thenReturn(strDeviceName);
        when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
        when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

        Device deviceDouble = mock(Device.class);

        when(deviceDouble.getID()).thenReturn(deviceIDDouble);
        when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
        when(deviceDouble.getDeviceName()).thenReturn(deviceNameDouble);
        when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
        when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

        DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

        //Act
        String result = deviceDataModel.getDeviceName();

        //Assert
        assertEquals(strDeviceName, result);
    }

    /**
     * Test getDeviceTypeID method.
     */
    @Test
    void shouldReturnDeviceTypeID_WhenGetDeviceTypeID(){
        //Arrange
        String strRoomID = "1";
        String strDeviceName = "Light";
        String strDeviceTypeID = "1";
        boolean deviceStatus = true;
        String strDeviceID = "1";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        RoomID roomIDDouble = mock(RoomID.class);
        DeviceName deviceNameDouble = mock(DeviceName.class);
        DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
        DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

        when(deviceIDDouble.getID()).thenReturn(strDeviceID);
        when(roomIDDouble.getID()).thenReturn(strRoomID);
        when(deviceNameDouble.getName()).thenReturn(strDeviceName);
        when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
        when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

        Device deviceDouble = mock(Device.class);

        when(deviceDouble.getID()).thenReturn(deviceIDDouble);
        when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
        when(deviceDouble.getDeviceName()).thenReturn(deviceNameDouble);
        when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
        when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

        DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

        //Act
        String result = deviceDataModel.getDeviceTypeID();

        //Assert
        assertEquals(strDeviceTypeID, result);
    }

    /**
     * Test getDeviceStatus method.
     */
    @Test
    void shouldReturnDeviceStatus_WhenGetDeviceStatus(){
        //Arrange
        String strRoomID = "1";
        String strDeviceName = "Light";
        String strDeviceTypeID = "1";
        boolean deviceStatus = true;
        String strDeviceID = "1";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        RoomID roomIDDouble = mock(RoomID.class);
        DeviceName deviceNameDouble = mock(DeviceName.class);
        DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
        DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

        when(deviceIDDouble.getID()).thenReturn(strDeviceID);
        when(roomIDDouble.getID()).thenReturn(strRoomID);
        when(deviceNameDouble.getName()).thenReturn(strDeviceName);
        when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
        when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

        Device deviceDouble = mock(Device.class);

        when(deviceDouble.getID()).thenReturn(deviceIDDouble);
        when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
        when(deviceDouble.getDeviceName()).thenReturn(deviceNameDouble);
        when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
        when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

        DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

        //Act
        boolean result = deviceDataModel.getDeviceStatus();

        //Assert
        assertEquals(deviceStatus, result);
    }



}

