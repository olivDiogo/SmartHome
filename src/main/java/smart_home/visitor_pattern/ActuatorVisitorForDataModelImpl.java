package smart_home.visitor_pattern;

import smart_home.domain.actuator.IActuator;
import smart_home.domain.actuator.blind_roller_actuator.BlindRollerActuator;
import smart_home.domain.actuator.set_decimal_actuator.SetDecimalActuator;
import smart_home.domain.actuator.set_integer_actuator.SetIntegerActuator;
import smart_home.domain.actuator.switch_actuator.SwitchActuator;
import smart_home.persistence.jpa.data_model.ActuatorDataModel;

public class ActuatorVisitorForDataModelImpl implements IActuatorVisitorForDataModel {

    private ActuatorDataModel _actuatorDataModel;

    public ActuatorVisitorForDataModelImpl(ActuatorDataModel actuatorDataModel) {
        validateActuatorDataModel(actuatorDataModel);
        this._actuatorDataModel = actuatorDataModel;
    }


    /**
     * Validate actuator data model.
     *
     * @param actuatorDataModel the actuator data model
     */
    private void validateActuatorDataModel(ActuatorDataModel actuatorDataModel) {
        if (actuatorDataModel == null) {
            throw new IllegalArgumentException("ActuatorDataModel cannot be null");
        }
    }

    /**
     * Gets actuator data model.
     *
     * @return the actuator data model
     */
    @Override
    public ActuatorDataModel getActuatorDataModel() {
        return _actuatorDataModel;
    }


    /**
     * Visitor for set integer actuator.
     *
     * @param setIntegerActuator the set integer actuator
     */
    @Override
    public void visitorSetIntegerActuator(SetIntegerActuator setIntegerActuator) {
        setGenericModelData(setIntegerActuator);
    }

    /**
     * Visitor for set decimal actuator.
     *
     * @param setDecimalActuator the set decimal actuator
     */
    @Override
    public void visitorSetDecimalActuator(SetDecimalActuator setDecimalActuator) {
        setGenericModelData(setDecimalActuator);
    }

    /**
     * Visitor for switch actuator.
     *
     * @param setBooleanActuator the set boolean actuator
     */
    @Override
    public void visitorSwitchActuator(SwitchActuator setBooleanActuator) {
        setGenericModelData(setBooleanActuator);
    }

    /**
     * Visitor for blind roller actuator.
     *
     * @param blindRollerActuator the blind roller actuator
     */
    @Override
    public void visitorBlindRollerActuator(BlindRollerActuator blindRollerActuator) {
        setGenericModelData(blindRollerActuator);
    }

    /**
     * Sets generic model data.
     *
     * @param actuator the actuator
     */
    private void setGenericModelData(IActuator actuator) {
        this._actuatorDataModel = new ActuatorDataModel(actuator);
    }
}
