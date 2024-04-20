package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.assembler.RoomDataModelAssembler;


class RoomRepositoryJPAImplTest {

    /**
     * Test class instantiation of RepositoryRoomJPAImpl.
     */
    @Test
    void shouldInstantiateRepositoryRoomJPAImpl_whenAttributesAreValid() {
        //Arrange
        RoomDataModelAssembler dataModelAssembler = new RoomDataModelAssembler(new RoomFactoryImpl());

        //Act
        RoomRepositoryJPAImpl repositoryRoomJPA = new RoomRepositoryJPAImpl(dataModelAssembler);

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

        String expectedMessage = "Data model assembler is required";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> new RoomRepositoryJPAImpl(dataModelAssembler));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


}
