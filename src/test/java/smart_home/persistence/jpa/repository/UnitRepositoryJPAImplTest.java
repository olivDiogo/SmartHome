package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smart_home.domain.unit.UnitFactoryImpl;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.assembler.UnitDataModelAssembler;

import static org.junit.jupiter.api.Assertions.*;

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
        Exception e = assertThrows(IllegalArgumentException.class, () -> new UnitRepositoryJPAImpl(dataModelAssembler));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}