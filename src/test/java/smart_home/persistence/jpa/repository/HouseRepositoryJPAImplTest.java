package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smart_home.persistence.assembler.IDataModelAssembler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HouseRepositoryJPAImplTest {
    /**
     * Test to verify if the HouseRepositoryJPAImplTest is instantiated correctly
     */
    @Test
    void shouldInstantiateHouseRepositoryJPAImpl_whenAttributesAreValid() {
        //Arrange
        IDataModelAssembler dataModelAssembler = mock(IDataModelAssembler.class);

        //Act
        HouseRepositoryJPAImpl houseRepositoryJPA = new HouseRepositoryJPAImpl(dataModelAssembler);

        //Assert
        assertNotNull(houseRepositoryJPA);
    }

    /**
     * Test to verify if the HouseRepositoryJPAImplTest throws an IllegalArgumentException when given a null data model assembler
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDataModelAssembler() {
        //Arrange
        IDataModelAssembler dataModelAssembler = null;

        String expectedMessage = "Data model assembler cannot be null.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HouseRepositoryJPAImpl(dataModelAssembler));
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}