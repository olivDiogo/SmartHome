package smart_home.persistence.mem;

import smart_home.ddd.IRepository;
import smart_home.domain.house.House;
import smart_home.domain.house.IHouseFactory;
import smart_home.domain.repository.IHouseRepository;
import smart_home.value_object.HouseID;

import java.util.*;

/**
 * This class represents a repository for houses, implementing the Repository interface.
 * It provides methods to save, find all, find by identity, and check if a house exists in the repository.
 */
public class HouseRepository implements IHouseRepository {
    private final Map<HouseID, House> _houseData = new LinkedHashMap<>();

    /**
     * Saves a house in the repository.
     *
     * @param house The house to be saved.
     * @return The saved house.
     * @throws IllegalArgumentException if the house is null or already exists in the repository.
     */
    @Override
    public House save(House house) {
        if (house == null) {
            throw new IllegalArgumentException("House cannot be null.");
        } else if (containsOfIdentity(house.getID())) {
            throw new IllegalArgumentException("House already exists.");
        } else {
            _houseData.put(house.getID(), house);
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
        return new ArrayList<>(_houseData.values());
    }

    /**
     * Finds a house by its identity.
     *
     * @param houseID The identity of the house to be found.
     * @return An Optional containing the house if found, or empty otherwise.
     */
    @Override
    public Optional<House> ofIdentity(HouseID houseID) {
        return Optional.ofNullable(_houseData.get(houseID));
    }

    /**
     * Checks if a house with the specified identity exists in the repository.
     *
     * @param houseID The identity of the house to check for existence.
     * @return true if the house exists in the repository, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(HouseID houseID) {
        return _houseData.containsKey(houseID);
    }
}