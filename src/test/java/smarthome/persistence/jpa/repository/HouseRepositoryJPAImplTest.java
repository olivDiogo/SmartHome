package smarthome.persistence.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.repository.IHouseRepository;
import smarthome.persistence.assembler.IDataModelAssembler;

class HouseRepositoryJPAImplTest {

  /**
   * Test to verify if the HouseRepositoryJPAImplTest is instantiated correctly
   */
  @Test
  void shouldInstantiateHouseRepositoryJPAImpl_whenAttributesAreValid() {
    //Arrange
    IDataModelAssembler dataModelAssembler = mock(IDataModelAssembler.class);

    //Act
    IHouseRepository houseRepositoryJPA = new HouseRepositoryJPAImpl(dataModelAssembler);

    //Assert
    assertNotNull(houseRepositoryJPA);
  }

  /**
   * Test to verify if the HouseRepositoryJPAImplTest throws an IllegalArgumentException when given
   * a null data model assembler
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDataModelAssembler() {
    //Arrange
    IDataModelAssembler dataModelAssembler = null;

    String expectedMessage = "Data model assembler cannot be null.";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HouseRepositoryJPAImpl(dataModelAssembler));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

}