package smarthome.utils.visitor_pattern;

import smarthome.persistence.jpa.data_model.ActuatorDataModel;

public interface IActuatorVisitorForDataModel extends IActuatorVisitor {

  ActuatorDataModel getActuatorDataModel();
}
