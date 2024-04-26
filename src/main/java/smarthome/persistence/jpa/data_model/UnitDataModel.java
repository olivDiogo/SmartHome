package smarthome.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.unit.Unit;
import smarthome.utils.Validator;


@Entity
@Table(name="unit")
public class UnitDataModel {

    @Id
    @Column(name = "UnitID")
    private String _unitID;

    @Column(name = "UnitSymbol")
    private String _unitSymbol;

    @Column(name = "UnitDescription")
    private String _unitDescription;

    @Version
    private long version;

    /**
     * Default constructor.
     */
    public UnitDataModel() {

    }

    /**
     * Constructs a UnitDataModel object from a Unit object.
     *
     * @param unit The Unit object to construct from.
     */
    public UnitDataModel(Unit unit){
        Validator.validateNotNull(unit, "Unit");
        this._unitID = unit.getID().getID();
        this._unitSymbol = unit.getUnitSymbol().getUnit();
        this._unitDescription = unit.getUnitDescription().getDescription();
    }

    /**
     * Gets the unit ID.
     *
     * @return The unit ID.
     */
    public String getUnitID(){
        return this._unitID;
    }

    /**
     * Gets the unit symbol.
     *
     * @return The unit symbol.
     */
    public String getUnitSymbol(){
        return this._unitSymbol;
    }

    /**
     * Gets the unit description.
     *
     * @return The unit description.
     */
    public String getUnitDescription(){
        return this._unitDescription;
    }
}


