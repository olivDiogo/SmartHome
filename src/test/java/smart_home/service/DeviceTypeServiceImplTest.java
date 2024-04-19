package smart_home.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import smart_home.domain.device_type.DeviceType;
import smart_home.domain.device_type.IDeviceTypeFactory;
import smart_home.persistence.mem.DeviceTypeRepository;
import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.TypeDescription;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeviceTypeServiceImplTest {

    /**
     * Test for the DeviceTypeService constructor.
     */
    @Test
    void shouldInstantiateDeviceTypeService_whenGivenValidParameters() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);

        //Act
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        //Assert
        assertNotNull(deviceTypeServiceImpl);
    }

    /**
     * Test for the DeviceTypeService constructor when the DeviceTypeRepository is null.
     */

    @Test
    void shouldThrowException_whenDeviceTypeRepositoryIsNull() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = null;
        IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
        String expectedMessage = "Please enter a valid device type repository.";

        //Act
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);
        });

        //Assert
        assertEquals(thrown.getMessage(), expectedMessage);
    }

    /**
     * Test for the DeviceTypeService constructor when the DeviceTypeFactory is null.
     */
    @Test
    void shouldThrowException_whenDeviceTypeFactoryIsNull() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        IDeviceTypeFactory deviceTypeFactory = null;
        String expectedMessage = "Please enter a valid device type factory.";

        //Act
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);
        });

        //Assert
        assertEquals(thrown.getMessage(), expectedMessage);
    }

    /**
     * Test for the addDeviceType method when the device type is created and saved to the repository.
     */
    @Test
    void shouldReturnTheDeviceType_whenDeviceTypeIsCreatedAndSavedToRepository() {
        //Arrange
        TypeDescription typeDescription = mock(TypeDescription.class);
        DeviceType deviceType = mock(DeviceType.class);

        IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
        when(deviceTypeFactory.createDeviceType(typeDescription)).thenReturn(deviceType);

        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        when(deviceTypeRepository.save(deviceType)).thenReturn(deviceType);

        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        //Act
        DeviceType resultDeviceType = deviceTypeServiceImpl.addDeviceType(typeDescription);

        //Assert
        assertEquals(deviceType, resultDeviceType);
    }

    /**
     * Test for the addDeviceType method when the device type is null.
     */
    @Test
    void shouldThrowException_whenDeviceTypeIsNull() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);
        TypeDescription typeDescription = null;
        String expectedMessage = "Please enter a valid device type.";

        //Act
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            deviceTypeServiceImpl.addDeviceType(typeDescription);
        });

        //Assert
        assertEquals(thrown.getMessage(), expectedMessage);
    }

    /**
     * Test for the getDeviceTypeByID method when a device typeID exists in the repository.
     */
    @Test
    void shouldFindDeviceTypeByID_whenDeviceTypeExistInRepository() {
        //Arrange
        DeviceType deviceType = mock(DeviceType.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        when(deviceTypeRepository.ofIdentity(deviceTypeID)).thenReturn(Optional.of(deviceType));


        IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        //Act
        DeviceType resultDeviceType = deviceTypeServiceImpl.getDeviceTypeByID(deviceTypeID).get();

        //Assert
        assertEquals(deviceType, resultDeviceType);
    }

    /**
     * Test for the getDeviceTypeByID method when a device type ID does not exist in the repository.
     */
    @Test
    void shouldThrowException_WhenFindingDeviceTypeByNullID() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);
        DeviceTypeID deviceTypeID = null;
        String expectedMessage = "Please enter a valid device type ID.";

        //Act
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            deviceTypeServiceImpl.getDeviceTypeByID(deviceTypeID);
        });

        //Assert
        assertEquals(thrown.getMessage(), expectedMessage);
    }

    /**
     * Test for the findAll method when there are device types in the repository.
     */
    @Test
    void shouldReturnAllDeviceTypes_whenFindingAllDeviceTypes() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        List<DeviceType> deviceTypes = deviceTypeRepository.findAll();

        //Act
        List<DeviceType> resultList = deviceTypeServiceImpl.getAllDeviceTypes();

        //Assert
        assertEquals(deviceTypes, resultList);
    }


    /**
     * Test for the findAll method when there are no device types in the repository.
     */
    @Test
    void shouldReturnEmptyList_WhenNoDeviceTypesExist() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        DeviceType deviceType = mock(DeviceType.class);
        List<DeviceType> deviceTypes = List.of(deviceType);
        when(deviceTypeRepository.findAll()).thenReturn(deviceTypes);


        //Act
        List<DeviceType> resultList = deviceTypeServiceImpl.getAllDeviceTypes();

        //Assert
        assertFalse(resultList.isEmpty());
        //assertEquals(deviceTypes, resultList);
    }

}
