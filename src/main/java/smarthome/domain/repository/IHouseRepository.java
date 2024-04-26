package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.house.House;
import smarthome.domain.value_object.HouseID;

public interface IHouseRepository extends IRepository<HouseID, House> {
}
