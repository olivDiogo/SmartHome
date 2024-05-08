package smarthome.ddd;

import smarthome.domain.exceptions.EmptyReturnException;
import java.util.List;

public interface IAssembler<ID extends IAggregateRoot, T extends IDTO> {

  /**
   * Method to convert a domain entity into a DTO.
   *
   * @param domainEntity is the domain entity to be converted.
   * @return the DTO.
   */
  T domainToDTO(ID domainEntity);

  /**
   * Method to convert a list of domain entities into a list of DTOs.
   *
   * @param domainEntities is the list of domain entities to be converted.
   * @return the list of DTOs.
   */

  List<T> domainToDTO(List<ID> domainEntities) throws EmptyReturnException;
}
