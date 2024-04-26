package smarthome.service;

import smarthome.ddd.IRepository;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.service.IHouseService;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.utils.Validator;

public class HouseServiceImpl implements IHouseService {

  final IHouseFactory houseFactory;

  final IRepository<HouseID, House> houseRepository;

  /**
   * Constructor for the HouseService class.
   *
   * @param houseFactory    the factory for creating House instances
   * @param houseRepository the repository for storing House instances
   */
  public HouseServiceImpl(IHouseFactory houseFactory, IRepository<HouseID, House> houseRepository) {
    Validator.validateNotNull(houseFactory, "House factory");
    Validator.validateNotNull(houseRepository, "House repository");
    this.houseFactory = houseFactory;
    this.houseRepository = houseRepository;
  }


  /**
   * Adds a new House to the repository.
   *
   * @param address the address of the house
   * @param gps     the GPS coordinates of the house
   * @return the newly created House
   */
  @Override
  public House addHouse(Address address, GPS gps) {
    House house = houseFactory.createHouse(address, gps);
    houseRepository.save(house);
    return house;
  }
}

