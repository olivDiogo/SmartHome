package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smart_home.domain.device.Device;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.DeviceDataModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RepositoryDeviceJPAImplTest {

    /**
     * Test to verify if the RepositoryDeviceJPAImpl is instantiated correctly
     */
    @Test
    void shouldInstantiateRepositoryDeviceJPAImpl_WHenGivenDataModelConverter() {
        //Arrange
        IDataModelAssembler<DeviceDataModel, Device> dataModelAssembler = mock(IDataModelAssembler.class);

        //Act
        RepositoryDeviceJPAImpl repositoryDeviceJPA = new RepositoryDeviceJPAImpl(dataModelAssembler);

        //Assert
        assertNotNull(repositoryDeviceJPA);
    }

    /**
     * Test to verify if the RepositoryDeviceJPAImpl throws an IllegalArgumentException when given a null data model converter
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
        //Arrange
        IDataModelAssembler<DeviceDataModel, Device> dataModelConverter = null;

        String expectedMessage = "The data model converter must not be null.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RepositoryDeviceJPAImpl(dataModelConverter));
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}
