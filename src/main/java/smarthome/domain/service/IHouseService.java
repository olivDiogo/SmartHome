package smarthome.domain.service;

import smarthome.ddd.IService;
import smarthome.domain.house.House;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import java.util.Optional;

public interface IHouseService extends IService {

  /**
   * Adds a new house to the repository.
   *
   * @param address the address of the house.
   * @param gps     the GPS coordinates of the house.
   * @return the house that was added.
   */
  House addHouse(Address address, GPS gps);

  House addHouse(HouseID houseID, Address address, GPS gps);

  Optional<House> getById(HouseID houseID);
}
