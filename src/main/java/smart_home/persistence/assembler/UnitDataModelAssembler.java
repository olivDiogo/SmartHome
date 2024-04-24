package smart_home.persistence.assembler;

import smart_home.domain.unit.IUnitFactory;
import smart_home.domain.unit.Unit;
import smart_home.persistence.jpa.data_model.UnitDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitID;
import smart_home.value_object.UnitSymbol;

import java.util.ArrayList;
import java.util.List;

public class UnitDataModelAssembler implements IDataModelAssembler<UnitDataModel, Unit>{
    private IUnitFactory unitFactory;

    /**
     * Class constructor
     *
     * @param unitFactory is the factory used to create Unit instances.
     */
    public UnitDataModelAssembler(IUnitFactory unitFactory) {
       Validator.validateNotNull(unitFactory, "Unit Factory");
        this.unitFactory = unitFactory;
    }



    /**
     * Converts a UnitDataModel instance to a Unit instance.
     *
     * @param unitDataModel is the domain entity to be converted.
     * @return a Unit instance.
     */
    @Override
    public Unit toDomain(UnitDataModel unitDataModel){
      Validator.validateNotNull(unitDataModel, "Unit Data Model");

        UnitID unitID = new UnitID(unitDataModel.getUnitID());
        UnitSymbol unitSymbol = new UnitSymbol(unitDataModel.getUnitSymbol());
        UnitDescription unitDescription = new UnitDescription(unitDataModel.getUnitDescription());

        Unit unit = unitFactory.createUnit(unitDescription, unitSymbol, unitID);

        return unit;
    }

    /**
     * Converts a list of UnitDataModel instances to a list of Unit instances.
     *
     * @param unitDataModels is the list of domain entities to be converted.
     * @return a list of Unit instances.
     */
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
