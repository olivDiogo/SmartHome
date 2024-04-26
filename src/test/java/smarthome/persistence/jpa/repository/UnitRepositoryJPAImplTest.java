package smarthome.persistence.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.assembler.UnitDataModelAssembler;

class UnitRepositoryJPAImplTest {

  /**
   * Test class instantiation of RepositoryUninJPAImpl.
   */
  @Test
  void shouldInstantiateRepositoryUninJPAImpl_whenAttributesAreValid() {
    //Arrange
    UnitDataModelAssembler dataModelAssembler = new UnitDataModelAssembler(new UnitFactoryImpl());

    //Act
    UnitRepositoryJPAImpl repositoryUninJPA = new UnitRepositoryJPAImpl(dataModelAssembler);

    //Assert
    assertNotNull(repositoryUninJPA);
  }

  /**
   * Test class instantiation of RepositoryUninJPAImpl when given null dataModelAssembler.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDataModelAssembler() {
    //Arrange
    IDataModelAssembler dataModelAssembler = null;

    String expectedMessage = "Data model assembler cannot be null.";

    //Act + Assert
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new UnitRepositoryJPAImpl(dataModelAssembler));

    //Assert
    String actualMessage = e.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

}