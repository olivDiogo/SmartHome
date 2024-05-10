package smarthome.persistence.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.jpa.data_model.DeviceDataModel;

class DeviceRepositoryJPAImplTest {

  /**
   * Test to verify if the RepositoryDeviceJPAImpl is instantiated correctly
   */
  @Test
  void shouldInstantiateRepositoryDeviceJPAImpl_WHenGivenDataModelConverter() {
    //Arrange
    IDataModelAssembler<DeviceDataModel, Device> dataModelAssembler = mock(
        IDataModelAssembler.class);

    //Act
    IDeviceRepository repositoryDeviceJPA = new DeviceRepositoryJPAImpl(dataModelAssembler);

    //Assert
    assertNotNull(repositoryDeviceJPA);
  }

  /**
   * Test to verify if the RepositoryDeviceJPAImpl throws an IllegalArgumentException when given a
   * null data model converter
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
    //Arrange
    IDataModelAssembler<DeviceDataModel, Device> dataModelConverter = null;

    String expectedMessage = "The data model converter must not be null.";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new DeviceRepositoryJPAImpl(dataModelConverter));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

}
