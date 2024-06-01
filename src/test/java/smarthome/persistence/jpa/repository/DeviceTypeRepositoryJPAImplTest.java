package smarthome.persistence.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.repository.IDeviceTypeRepository;
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
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    //Act
    IDeviceTypeRepository repositoryDeviceTypeJPA = new DeviceTypeRepositoryJPAImpl(
        dataModelAssembler, entityManagerFactory);

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
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    String expectedMessage = "Data model assembler cannot be null.";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new DeviceTypeRepositoryJPAImpl(dataModelAssembler, entityManagerFactory));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

}