package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smart_home.domain.device_type.DeviceType;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.DeviceTypeDataModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DeviceTypeRepositoryJPAImplTest {
    /**
     * Test to verify if the {@link DeviceTypeRepositoryJPAImpl} is instantiated correctly
     */
    @Test
    void shouldInstantiateRepositoryDeviceTypeJPAImpl_whenAttributesAreValid() {
        //Arrange
        IDataModelAssembler<DeviceTypeDataModel, DeviceType> dataModelAssembler = mock(IDataModelAssembler.class);
        //Act
        DeviceTypeRepositoryJPAImpl repositoryDeviceTypeJPA = new DeviceTypeRepositoryJPAImpl(dataModelAssembler);

        //Assert
        assertNotNull(repositoryDeviceTypeJPA);
    }

    /**
     * Test to verify if the {@link DeviceTypeRepositoryJPAImpl} throws an IllegalArgumentException when given a null data model assembler
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDataModelAssembler() {
        //Arrange
        IDataModelAssembler<DeviceTypeDataModel, DeviceType> dataModelAssembler = null;

        String expectedMessage = "Data model assembler cannot be null.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DeviceTypeRepositoryJPAImpl(dataModelAssembler));
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}