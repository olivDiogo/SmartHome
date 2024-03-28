package SmartHomeDDD.service;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.DeviceType.DeviceType;
import SmartHomeDDD.domain.DeviceType.DeviceTypeFactory;
import SmartHomeDDD.domain.DeviceType.ImpDeviceTypeFactory;
import SmartHomeDDD.repository.DeviceTypeRepository;
import SmartHomeDDD.valueObject.DeviceTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeviceTypeServiceTest {

    @Test
    void shouldInstantiateDeviceTypeService_whenGivenValidParameters() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        DeviceTypeFactory deviceTypeFactory = mock(DeviceTypeFactory.class);

        //Act
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);
    }

    @Test
    void shouldThrowException_whenDeviceTypeRepositoryIsNull() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = null;
        DeviceTypeFactory deviceTypeFactory = mock(DeviceTypeFactory.class);
        String expectedMessage = "Please enter a valid device type repository.";

        //Act
        IllegalArgumentException thrown = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);
        });

        //Assert
        assertEquals(thrown.getMessage(), expectedMessage);
    }

    @Test
    void shouldThrowException_whenDeviceTypeFactoryIsNull() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        DeviceTypeFactory deviceTypeFactory = null;
        String expectedMessage = "Please enter a valid device type factory.";

        //Act
        IllegalArgumentException thrown = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);
        });

        //Assert
        assertEquals(thrown.getMessage(), expectedMessage);
    }

    @Test
    void shouldCreateDeviceType_whenGivenValidParameters() {
        //Arrange
        TypeDescription typeDescription = mock(TypeDescription.class);
        DeviceType deviceType = mock(DeviceType.class);

        DeviceTypeFactory deviceTypeFactory = mock(DeviceTypeFactory.class);
        when(deviceTypeFactory.createDeviceType(typeDescription)).thenReturn(deviceType);

        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        //Act
        DeviceType resultDeviceType = deviceTypeService.createDeviceType(typeDescription);

        //Assert
        assertEquals(deviceType, resultDeviceType);
    }

    @Test
    void shouldThrowException_whenDeviceTypeIsNull() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        DeviceTypeFactory deviceTypeFactory = mock(DeviceTypeFactory.class);

        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);
        DeviceType deviceType = null;
        String expectedMessage = "Please enter a valid device type.";

        //Act
        IllegalArgumentException thrown = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceTypeService.saveDeviceType(deviceType);
        });

        //Assert
        assertEquals(thrown.getMessage(), expectedMessage);
    }

    @Test
    void shouldSaveDeviceType_whenGivenValidParameters() {
        //Arrange
        DeviceType deviceType = mock(DeviceType.class);
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        when(deviceTypeRepository.save(deviceType)).thenReturn(deviceType);
        DeviceTypeFactory deviceTypeFactory = mock(DeviceTypeFactory.class);

        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        //Act
        DeviceType resultDeviceType = deviceTypeService.saveDeviceType(deviceType);

        //Assert
        assertEquals(deviceType, resultDeviceType);

    }

    @Test
    void shouldThrowException_whenSavingNullDeviceType() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        DeviceTypeFactory deviceTypeFactory = mock(DeviceTypeFactory.class);
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);
        DeviceType deviceType = null;
        String expectedMessage = "Please enter a valid device type.";

        //Act
        IllegalArgumentException thrown = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceTypeService.saveDeviceType(deviceType);
        });

        //Assert
        assertEquals(thrown.getMessage(), expectedMessage);
    }

    @Test
    void shouldFindDeviceTypeByID_whenDeviceTypeExistInRepository(){
        //Arrange
        DeviceType deviceType = mock(DeviceType.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        when(deviceTypeRepository.ofIdentity(deviceTypeID)).thenReturn(Optional.of(deviceType));


        DeviceTypeFactory deviceTypeFactory = mock(DeviceTypeFactory.class);
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        //Act
        DeviceType resultDeviceType = deviceTypeService.getDeviceTypeByID(deviceTypeID).get();

        //Assert
        assertEquals(deviceType, resultDeviceType);
    }

    @Test
    void shouldThrowException_WhenFindingDeviceTypeByNullID(){
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        DeviceTypeFactory deviceTypeFactory = mock(DeviceTypeFactory.class);
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);
        DeviceTypeID deviceTypeID = null;
        String expectedMessage = "Please enter a valid device type ID.";

        //Act
        IllegalArgumentException thrown = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceTypeService.getDeviceTypeByID(deviceTypeID);
        });

        //Assert
        assertEquals(thrown.getMessage(), expectedMessage);
    }

    @Test
    void shouldReturnAllDeviceTypes_whenFindingAllDeviceTypes() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        DeviceTypeFactory deviceTypeFactory = mock(DeviceTypeFactory.class);
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        //Act
        deviceTypeService.findAllDeviceTypes();

        //Assert
        assertEquals(deviceTypeRepository.findAll(), deviceTypeService.findAllDeviceTypes());
    }

    @Test
    void shouldReturnEmptyList_WhenNoDeviceTypesExist() {
        //Arrange
        DeviceTypeRepository deviceTypeRepository = mock(DeviceTypeRepository.class);
        DeviceTypeFactory deviceTypeFactory = mock(DeviceTypeFactory.class);
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        //Act
        deviceTypeService.findAllDeviceTypes();

        //Assert
        assertEquals(deviceTypeRepository.findAll(), deviceTypeService.findAllDeviceTypes());
    }

}
