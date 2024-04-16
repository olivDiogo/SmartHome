package smartHome.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smartHome.domain.room.RoomFactoryImpl;
import smartHome.persistence.assembler.IDataModelAssembler;
import smartHome.persistence.assembler.RoomDataModelAssembler;

import static org.junit.Assert.*;

public class RepositoryRoomJPAImplTest {

    /**
     * Test class instantiation of RepositoryRoomJPAImpl.
     */
    @Test
    void shouldInstantiateRepositoryRoomJPAImpl_whenAttributesAreValid() {
        //Arrange
        RoomDataModelAssembler dataModelAssembler = new RoomDataModelAssembler(new RoomFactoryImpl());

        //Act
        RepositoryRoomJPAImpl repositoryRoomJPA = new RepositoryRoomJPAImpl(dataModelAssembler);

        //Assert
        assertNotNull(repositoryRoomJPA);
    }

    /**
     * Test class instantiation of RepositoryRoomJPAImpl when given null dataModelAssembler.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDataModelAssembler() {
        //Arrange
        IDataModelAssembler dataModelAssembler = null;

        String expectedMessage = "Data model assembler cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> new RepositoryRoomJPAImpl(dataModelAssembler));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


}
