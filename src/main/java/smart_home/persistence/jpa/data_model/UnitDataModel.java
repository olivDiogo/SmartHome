package smart_home.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import smart_home.domain.unit.Unit;


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
        validateUnit(unit);
        this._unitID = unit.getID().getID();
        this._unitSymbol = unit.getUnitSymbol().getUnit();
        this._unitDescription = unit.getUnitDescription().getDescription();
    }

    /**
     * Validates the provided Unit object.
     *
     * @param unit The Unit object to validate.
     * @throws IllegalArgumentException If the provided unit is null.
     */
    private void validateUnit(Unit unit){
        if(unit == null){
            throw new IllegalArgumentException("Unit cannot be null.");
        }
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


