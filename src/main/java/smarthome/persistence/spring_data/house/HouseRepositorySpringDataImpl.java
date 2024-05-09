package smarthome.persistence.spring_data.house;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.house.House;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.value_object.HouseID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.jpa.data_model.HouseDataModel;
import smarthome.utils.Validator;

@Repository
public class HouseRepositorySpringDataImpl implements IHouseRepository {

  private final IHouseSpringDataRepository repository;
  private final IDataModelAssembler<HouseDataModel, House> assembler;

  /**
   * Constructs a new repository instance with the specified repository and data model assembler.
   *
   * @param repository the repository to use
   * @param assembler  the converter to transform data models to domain models and vice versa
   */
  public HouseRepositorySpringDataImpl(IHouseSpringDataRepository repository,
      IDataModelAssembler<HouseDataModel, House> assembler) {
    Validator.validateNotNull(repository, "House repository");
    this.repository = repository;
    Validator.validateNotNull(assembler, "House data model assembler");
    this.assembler = assembler;
  }

  /**
   * Saves the specified house entity to the database.
   *
   * @param house the house entity to save
   * @return the saved house entity
   * @throws IllegalArgumentException if the entity is null
   */
  @Override
  @Transactional
  public House save(House house) {
    Validator.validateNotNull(house, "House");
    HouseDataModel dataModel = new HouseDataModel(house);
    repository.save(dataModel);
    return house;
  }


  /**
   * Finds all house entities in the database.
   *
   * @return a list of all house entities
   */
  @Override
  public List<House> findAll() {
    List<HouseDataModel> houseDataModels = this.repository.findAll();
    List<House> houses = assembler.toDomain(houseDataModels);
    return houses;
  }

  /**
   * Finds the house entity with the specified identity.
   *
   * @param objectID the identity of the house entity to find
   * @return an optional containing the house entity with the specified identity, or an empty
   * optional if no house entity with the specified identity was found
   * @throws IllegalArgumentException if the identity is null
   */
  @Override
  public Optional<House> ofIdentity(HouseID objectID) {
    Optional<HouseDataModel> houseDataModel = this.repository.findById(objectID.toString());
    if (houseDataModel.isPresent()) {
      HouseDataModel houseDataModel1 = houseDataModel.get();
      House house = assembler.toDomain(houseDataModel1);
      return Optional.of(house);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Checks if a house entity with the specified identity exists in the database.
   *
   * @param objectID the identity of the house entity to check
   * @return true if a house entity with the specified identity exists in the database, false
   * otherwise
   * @throws IllegalArgumentException if the identity is null
   */
  @Override
  public boolean containsOfIdentity(HouseID objectID) {
    return this.repository.existsById(objectID.getID());
  }
}
