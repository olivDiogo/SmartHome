package smarthome.utils.visitor_pattern;

import smarthome.persistence.jpa.data_model.SensorDataModel;

public interface ISensorVisitorForDataModel extends ISensorVisitor{
    SensorDataModel getSensorDataModel();
}
