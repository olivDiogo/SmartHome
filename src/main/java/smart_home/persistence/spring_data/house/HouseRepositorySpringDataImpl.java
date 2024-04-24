package smart_home.persistence.spring_data.house;

import java.util.List;
import java.util.Optional;
import smart_home.domain.house.House;
import smart_home.domain.repository.IHouseRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.HouseDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.HouseID;

public class HouseRepositorySpringDataImpl implements IHouseRepository {

  private final IHouseSpringDataRepository _repository;
  private final IDataModelAssembler<HouseDataModel, House> _assembler;

  public HouseRepositorySpringDataImpl(IHouseSpringDataRepository repository, IDataModelAssembler<HouseDataModel, House> assembler) {
    Validator.validateNotNull(repository, "House repository");
    this._repository = repository;
    Validator.validateNotNull(assembler, "House data model assembler");
    this._assembler = assembler;
  }

  @Override
  public House save(House house) {
    Validator.validateNotNull(house, "House");
    HouseDataModel dataModel = new HouseDataModel(house);
    _repository.save(dataModel);
    return house;  }


  @Override
  public List<House> findAll() {
    List<HouseDataModel> houseDataModels = this._repository.findAll();
    List<House> houses = _assembler.toDomain(houseDataModels);
    return houses;
  }

  @Override
  public Optional<House> ofIdentity(HouseID objectID) {
    Optional<HouseDataModel> houseDataModel = this._repository.findById(objectID.toString());
    if (houseDataModel.isPresent()) {
      HouseDataModel houseDataModel1 = houseDataModel.get();
      House house = _assembler.toDomain(houseDataModel1);
      return Optional.of(house);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public boolean containsOfIdentity(HouseID objectID) {
    return this._repository.existsById(objectID.toString());
  }
}
