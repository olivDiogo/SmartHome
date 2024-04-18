package smart_home.visitor_pattern;

import smart_home.persistence.jpa.data_model.ActuatorDataModel;

public interface IActuatorVisitorForDataModel extends IActuatorVisitor {

    ActuatorDataModel getActuatorDataModel();
}
