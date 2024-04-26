package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitID;

public interface IUnitRepository extends IRepository<UnitID, Unit> {
}
