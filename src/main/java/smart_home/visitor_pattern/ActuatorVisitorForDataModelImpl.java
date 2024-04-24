package smart_home.visitor_pattern;

import smart_home.domain.actuator.IActuator;
import smart_home.domain.actuator.blind_roller_actuator.BlindRollerActuator;
import smart_home.domain.actuator.set_decimal_actuator.SetDecimalActuator;
import smart_home.domain.actuator.set_integer_actuator.SetIntegerActuator;
import smart_home.domain.actuator.switch_actuator.SwitchActuator;
import smart_home.persistence.jpa.data_model.ActuatorDataModel;
import smart_home.utils.Validator;

public class ActuatorVisitorForDataModelImpl implements IActuatorVisitorForDataModel {

    private ActuatorDataModel actuatorDataModel;

    public ActuatorVisitorForDataModelImpl(ActuatorDataModel actuatorDataModel) {
        Validator.validateNotNull(actuatorDataModel, "Actuator Data Model");
        this.actuatorDataModel = actuatorDataModel;
    }



    /**
     * Gets actuator data model.
     *
     * @return the actuator data model
     */
    @Override
    public ActuatorDataModel getActuatorDataModel() {
        return actuatorDataModel;
    }


    /**
     * Visitor for set integer actuator.
     *
     * @param setIntegerActuator the set integer actuator
     */
    @Override
    public String visitorSetIntegerActuator(SetIntegerActuator setIntegerActuator) {
        setGenericModelData(setIntegerActuator);
         int min = setIntegerActuator.getLimits().getLowerLimit();
        int max = setIntegerActuator.getLimits().getUpperLimit();
        this.actuatorDataModel.setIntegerLowerBond(min);
        this.actuatorDataModel.setIntegerUpperBond(max);
        return actuatorDataModel.toString();
    }

    /**
     * Visitor for set decimal actuator.
     *
     * @param setDecimalActuator the set decimal actuator
     */
    @Override
    public String visitorSetDecimalActuator(SetDecimalActuator setDecimalActuator) {
        setGenericModelData(setDecimalActuator);
        double min = setDecimalActuator.getLimits().getLowerLimit();
        double max = setDecimalActuator.getLimits().getUpperLimit();
        this.actuatorDataModel.setDecimalLowerBond(min);
        this.actuatorDataModel.setDecimalUpperBond(max);
        return actuatorDataModel.toString();
    }

    /**
     * Visitor for switch actuator.
     *
     * @param setBooleanActuator the set boolean actuator
     */
    @Override
    public String visitorSwitchActuator(SwitchActuator setBooleanActuator) {
        setGenericModelData(setBooleanActuator);
        return actuatorDataModel.toString();
    }

    /**
     * Visitor for blind roller actuator.
     *
     * @param blindRollerActuator the blind roller actuator
     */
    @Override
    public String visitorBlindRollerActuator(BlindRollerActuator blindRollerActuator) {
        setGenericModelData(blindRollerActuator);
        return actuatorDataModel.toString();
    }

    /**
     * Sets generic model data.
     *
     * @param actuator the actuator
     */
    private void setGenericModelData(IActuator actuator) {
        this.actuatorDataModel.setGenericActuatorParameters(actuator);
    }
}
