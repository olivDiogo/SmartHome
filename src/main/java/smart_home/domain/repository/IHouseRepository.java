package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.house.House;
import smart_home.value_object.HouseID;

public interface IHouseRepository extends IRepository<HouseID, House> {
}
