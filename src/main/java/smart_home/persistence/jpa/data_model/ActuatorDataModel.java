package smart_home.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import smart_home.domain.actuator.IActuator;

@Entity
@Table(name = "Actuator")
public class ActuatorDataModel {

    @Id
    private String _actuatorID;
    @Column (name = "deviceID")
    private String _deviceID;
    @Column (name = "modelPath")
    private String _modelPath;
    @Column (name = "actuatorTypeID")
    private String _actuatorTypeID;
    @Column (name = "actuatorName")
    private String _actuatorName;


    public ActuatorDataModel() {
    }

    /**
     * Class constructor
     */
    public ActuatorDataModel(IActuator actuator) {
        validateActuator(actuator);
        this._actuatorID = actuator.getID().getID();
        this._deviceID = actuator.getDeviceID().getID();
        this._modelPath = actuator.getModelPath().getID();
        this._actuatorTypeID = actuator.getActuatorTypeID().getID();
        this._actuatorName = actuator.getName().getActuatorName();

    }

    /**
     * Method to validate actuator
     *
     * @param actuator is the actuator to be validated
     */
    private void validateActuator(IActuator actuator) {
        if (actuator == null) {
            throw new IllegalArgumentException("Actuator cannot be null.");
        }
    }

    /**
     * Method to return the actuator ID.
     *
     * @return the actuator ID
     */
    public String getActuatorID() {
        return this._actuatorID;
    }

    /**
     * Method to return the device ID.
     *
     * @return the device ID
     */
    public String getDeviceID() {
        return this._deviceID;
    }

    /**
     * Method to return the model path.
     *
     * @return the model path
     */
    public String getModelPath() {
        return this._modelPath;
    }

    /**
     * Method to return the actuator type ID.
     *
     * @return the actuator type ID
     */
    public String getActuatorTypeID() {
        return this._actuatorTypeID;
    }

    /**
     * Method to return the actuator name.
     *
     * @return the actuator name
     */
    public String getActuatorName() {
        return this._actuatorName;
    }


}
