package smart_home.persistence.assembler;

import smart_home.domain.unit.IUnitFactory;
import smart_home.domain.unit.Unit;
import smart_home.persistence.jpa.data_model.UnitDataModel;
import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitID;
import smart_home.value_object.UnitSymbol;

import java.util.ArrayList;
import java.util.List;

public class UnitDataModelAssembler implements IDataModelAssembler<UnitDataModel, Unit>{
    private IUnitFactory _unitFactory;

    public UnitDataModelAssembler(IUnitFactory unitFactory) {
        validateUnitFactory(unitFactory);
        _unitFactory = unitFactory;
    }

    private void validateUnitFactory(IUnitFactory unitFactory){
        if(unitFactory == null){
            throw new IllegalArgumentException("UnitFactory cannot be null.");
        }
    }

    @Override
    public Unit toDomain(UnitDataModel unitDataModel){
        if(unitDataModel == null){
            throw new IllegalArgumentException("UnitDataModel cannot be null.");
        }
        UnitID unitID = new UnitID(unitDataModel.getUnitID());
        UnitSymbol unitSymbol = new UnitSymbol(unitDataModel.getUnitSymbol());
        UnitDescription unitDescription = new UnitDescription(unitDataModel.getUnitDescription());

        Unit unit = _unitFactory.createUnit(unitDescription, unitSymbol);

        return unit;
    }

    @Override
    public List<Unit> toDomain(List<UnitDataModel> unitDataModels){
        List<Unit> units = new ArrayList<>();

        for (UnitDataModel unitDataModel : unitDataModels){
            Unit unit = toDomain(unitDataModel);
            units.add(unit);
        }
        return units;
    }

}
