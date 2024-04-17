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

        public UnitDataModel() {

        }
        public UnitDataModel(Unit unit){
            validateUnit(unit);
            this._unitID = unit.getID().getID();
            this._unitSymbol = unit.getUnitSymbol().getUnit();
            this._unitDescription = unit.getUnitDescription().getDescription();
        }

        private void validateUnit(Unit unit){
            if(unit == null){
                throw new IllegalArgumentException("Unit cannot be null.");
            }
        }

        public String getUnitID(){
            return this._unitID;
        }

        public String getUnitSymbol(){
            return this._unitSymbol;
        }

        public String getUnitDescription(){
            return this._unitDescription;
        }

    }


