package smarthome.persistence.assembler;

import java.util.ArrayList;
import java.util.List;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.IDeviceTypeFactory;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.persistence.jpa.data_model.DeviceTypeDataModel;
import smarthome.utils.Validator;

public class DeviceTypeDataModelAssembler implements
    IDataModelAssembler<DeviceTypeDataModel, DeviceType> {

  private final IDeviceTypeFactory deviceTypeFactory;

  /**
   * Constructor of DeviceTypeDataModelAssembler
   *
   * @param deviceTypeFactory the deviceTypeFactory to be used
   */
  public DeviceTypeDataModelAssembler(IDeviceTypeFactory deviceTypeFactory) {
    Validator.validateNotNull(deviceTypeFactory, "Device Type Factory");
    this.deviceTypeFactory = deviceTypeFactory;
  }


  @Override
  public DeviceType toDomain(DeviceTypeDataModel deviceTypeDataModel) {
    Validator.validateNotNull(deviceTypeDataModel, "Device Type Data Model");

    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceTypeDataModel.getDeviceTypeID());
    TypeDescription typeDescription = new TypeDescription(
        deviceTypeDataModel.getDeviceTypeDescription());
    return deviceTypeFactory.createDeviceType(deviceTypeID, typeDescription);
  }

  @Override
  public List<DeviceType> toDomain(List<DeviceTypeDataModel> deviceTypeDataModels) {
    if (deviceTypeDataModels == null || deviceTypeDataModels.isEmpty()) {
      throw new IllegalArgumentException("The list of device types cannot be null or empty.");
    }

    List<DeviceType> deviceTypes = new ArrayList<>();

    for (DeviceTypeDataModel deviceTypeDataModel : deviceTypeDataModels) {
      DeviceType deviceType = toDomain(deviceTypeDataModel);
      deviceTypes.add(deviceType);
    }

    return deviceTypes;
  }
}
