package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.house.House;
import smart_home.value_object.Address;
import smart_home.value_object.GPS;

public interface IHouseService extends IService {
    /**
     * Adds a new house to the repository.
     *
     * @param address the address of the house.
     * @param gps     the GPS coordinates of the house.
     * @return the house that was added.
     */
    House addHouse(Address address, GPS gps);
}
