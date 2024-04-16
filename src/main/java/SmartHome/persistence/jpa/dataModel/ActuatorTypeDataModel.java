package smartHome.persistence.jpa.dataModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import smartHome.domain.actuatorType.ActuatorType;

@Entity
@Table(name = "ActuatorType")
public class ActuatorTypeDataModel {
    @Id
    private String _actuatorTypeID;
    @Column(name = "actuatorTypeName")
    private String _actuatorTypeName;
    @Column(name = "unitID")
    private String _unitID;

    /**
     * Default constructor.
     */
    public ActuatorTypeDataModel() {
    }

    /**
     * Constructor that initializes the ActuatorTypeDataModel with the given ActuatorType.
     * @param actuatorType is the ActuatorType to be used to initialize the ActuatorTypeDataModel.
     */
    public ActuatorTypeDataModel(ActuatorType actuatorType) {
        validateActuatorType(actuatorType);
        this._actuatorTypeID = actuatorType.getID().getID();
        this._actuatorTypeName = actuatorType.getActuatorTypeName().toString();
        this._unitID = actuatorType.getUnit().getID();
    }

    /**
     * Validates the ActuatorType.
     * @param actuatorType is the ActuatorType to be validated.
     */
    private void validateActuatorType(ActuatorType actuatorType) {
        if (actuatorType == null) {
            throw new IllegalArgumentException("ActuatorType cannot be null.");
        }
    }

    /**
     * Getter for the actuator type ID.
     * @return the actuator type ID.
     */
    public String getActuatorTypeID() {
        return this._actuatorTypeID;
    }

    /**
     * Getter for the actuator type name.
     * @return the actuator type name.
     */
    public String getActuatorTypeName() {
        return this._actuatorTypeName;
    }

    /**
     * Getter for the unit ID.
     * @return the unit ID.
     */
    public String getUnitID() {
        return this._unitID;
    }
}

