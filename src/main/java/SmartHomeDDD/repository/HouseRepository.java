package SmartHomeDDD.repository;

import SmartHomeDDD.ValueObject.HouseID;
import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.House.House;

import java.util.*;

public class HouseRepository implements Repository<HouseID, House> {
    private final Map<HouseID, House> _houseData = new LinkedHashMap<>();

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

    @Override
    public List<House> findAll() {
        List<House> allHouses = _houseData.values().stream().toList();
        return allHouses;
    }

    @Override
    public Optional<House> ofIdentity(HouseID houseID) {
        Optional<House> house = Optional.ofNullable(_houseData.get(houseID));
        return house;
    }

    @Override
    public boolean containsOfIdentity(HouseID houseID) {
        return _houseData.containsKey(houseID);
    }
}
