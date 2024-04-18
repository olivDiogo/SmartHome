package smart_home.visitor_pattern;

import smart_home.persistence.jpa.data_model.SensorDataModel;

public interface ISensorVisitorForDataModel extends ISensorVisitor{
    SensorDataModel getSensorDataModel();
}
