package smarthome.persistence.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.assembler.RoomDataModelAssembler;


class RoomRepositoryJPAImplTest {

  /**
   * Test class instantiation of RepositoryRoomJPAImpl.
   */
  @Test
  void shouldInstantiateRepositoryRoomJPAImpl_whenAttributesAreValid() {
    //Arrange
    RoomDataModelAssembler dataModelAssembler = new RoomDataModelAssembler(new RoomFactoryImpl());
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    //Act
    IRoomRepository repositoryRoomJPA = new RoomRepositoryJPAImpl(dataModelAssembler,
        entityManagerFactory);

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
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    String expectedMessage = "Data model assembler is required";

    //Act + Assert
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new RoomRepositoryJPAImpl(dataModelAssembler, entityManagerFactory));

    //Assert
    String actualMessage = e.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }


}
