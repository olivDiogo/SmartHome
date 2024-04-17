package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.unit.Unit;
import smart_home.value_object.UnitID;

public interface IUnitRepository extends IRepository<UnitID, Unit> {
}
