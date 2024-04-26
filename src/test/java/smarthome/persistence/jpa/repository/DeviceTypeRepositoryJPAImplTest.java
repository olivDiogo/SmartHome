package smarthome.persistence.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.device_type.DeviceType;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.jpa.data_model.DeviceTypeDataModel;

class DeviceTypeRepositoryJPAImplTest {

  /**
   * Test to verify if the {@link DeviceTypeRepositoryJPAImpl} is instantiated correctly
   */
  @Test
  void shouldInstantiateRepositoryDeviceTypeJPAImpl_whenAttributesAreValid() {
    //Arrange
    IDataModelAssembler<DeviceTypeDataModel, DeviceType> dataModelAssembler = mock(
        IDataModelAssembler.class);
    //Act
    DeviceTypeRepositoryJPAImpl repositoryDeviceTypeJPA = new DeviceTypeRepositoryJPAImpl(
        dataModelAssembler);

    //Assert
    assertNotNull(repositoryDeviceTypeJPA);
  }

  /**
   * Test to verify if the {@link DeviceTypeRepositoryJPAImpl} throws an IllegalArgumentException
   * when given a null data model assembler
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDataModelAssembler() {
    //Arrange
    IDataModelAssembler<DeviceTypeDataModel, DeviceType> dataModelAssembler = null;

    String expectedMessage = "Data model assembler cannot be null.";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new DeviceTypeRepositoryJPAImpl(dataModelAssembler));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

}