package smarthome.persistence.mem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import smarthome.domain.house.House;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.value_object.HouseID;
import smarthome.utils.Validator;

public class HouseRepository implements IHouseRepository {

  private final Map<HouseID, House> DATA = new LinkedHashMap<>();

  /**
   * Saves a house in the repository.
   *
   * @param house The house to be saved.
   * @return The saved house.
   * @throws IllegalArgumentException if the house is null or already exists in the repository.
   */
  @Override
  public House save(House house) {
    Validator.validateNotNull(house, "House");

    if (containsOfIdentity(house.getID())) {
      throw new IllegalArgumentException("House already exists.");
    } else {
      DATA.put(house.getID(), house);
    }
    return house;
  }

  /**
   * Finds all houses in the repository.
   *
   * @return A list containing all houses in the repository.
   */
  @Override
  public List<House> findAll() {
    return new ArrayList<>(DATA.values());
  }

  /**
   * Finds a house by its identity.
   *
   * @param houseID The identity of the house to be found.
   * @return An Optional containing the house if found, or empty otherwise.
   */
  @Override
  public Optional<House> ofIdentity(HouseID houseID) {
    return Optional.ofNullable(DATA.get(houseID));
  }

  /**
   * Checks if a house with the specified identity exists in the repository.
   *
   * @param houseID The identity of the house to check for existence.
   * @return true if the house exists in the repository, false otherwise.
   */
  @Override
  public boolean containsOfIdentity(HouseID houseID) {
    return DATA.containsKey(houseID);
  }
}