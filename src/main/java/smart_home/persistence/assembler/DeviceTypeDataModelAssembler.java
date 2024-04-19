package smart_home.persistence.assembler;

import smart_home.domain.device_type.DeviceType;
import smart_home.domain.device_type.IDeviceTypeFactory;
import smart_home.persistence.jpa.data_model.DeviceTypeDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.TypeDescription;

import java.util.ArrayList;
import java.util.List;

public class DeviceTypeDataModelAssembler implements IDataModelAssembler <DeviceTypeDataModel, DeviceType> {
    private IDeviceTypeFactory _deviceTypeFactory;

    /**
     * Constructor of DeviceTypeDataModelAssembler
     * @param deviceTypeFactory the deviceTypeFactory to be used
     */
    public DeviceTypeDataModelAssembler(IDeviceTypeFactory deviceTypeFactory) {
      Validator.validateNotNull(deviceTypeFactory, "Device Type Factory");
    this._deviceTypeFactory = deviceTypeFactory;
  }


    @Override
    public DeviceType toDomain(DeviceTypeDataModel deviceTypeDataModel) {
    Validator.validateNotNull(deviceTypeDataModel, "Device Type Data Model");

    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceTypeDataModel.getDeviceTypeID());
    TypeDescription typeDescription = new TypeDescription(deviceTypeDataModel.getDeviceTypeDescription());
    return _deviceTypeFactory.createDeviceType(deviceTypeID, typeDescription);
    }

    @Override
    public List<DeviceType> toDomain(List<DeviceTypeDataModel> deviceTypeDataModels) {
    if (deviceTypeDataModels == null || deviceTypeDataModels.isEmpty())
      throw new IllegalArgumentException("The list of device types cannot be null or empty.");

    List<DeviceType> deviceTypes = new ArrayList<>();

    for (DeviceTypeDataModel deviceTypeDataModel : deviceTypeDataModels) {
      DeviceType deviceType = toDomain(deviceTypeDataModel);
      deviceTypes.add(deviceType);
    }

    return deviceTypes;
    }
}
