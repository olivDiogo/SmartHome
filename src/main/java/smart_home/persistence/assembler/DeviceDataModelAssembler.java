package smart_home.persistence.assembler;


import smart_home.domain.device.Device;
import smart_home.domain.device.IDeviceFactory;
import smart_home.persistence.jpa.data_model.DeviceDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DeviceDataModelConverter} class provides methods to convert {@link DeviceDataModel}
 * objects into their domain representation {@link Device}, and vice versa.
 * This class is part of the data access layer and facilitates the transformation
 * between persistence models and domain models, enabling the separation of concerns.
 */
public class DeviceDataModelAssembler implements IDataModelAssembler<DeviceDataModel, Device> {

    private IDeviceFactory _deviceFactory;

    /**
     * Constructs a DeviceDataModelConverter with a specific {@link IDeviceFactory}.
     *
     * @param deviceFactory The factory used to create Device domain objects.
     */
    public DeviceDataModelAssembler(IDeviceFactory deviceFactory) {
       Validator.validateNotNull(deviceFactory, "Device Factory");
        _deviceFactory = deviceFactory;
    }

    /**
     * Converts a {@link DeviceDataModel} object to a {@link Device} domain object.
     *
     * @param deviceDataModel The device data model to convert.
     * @return The corresponding Device domain object.
     */
    @Override
    public Device toDomain(DeviceDataModel deviceDataModel) {
       Validator.validateNotNull(deviceDataModel, "Device Data Model");

        RoomID roomID = new RoomID(deviceDataModel.getRoomID());
        DeviceName deviceName = new DeviceName(deviceDataModel.getDeviceName());
        DeviceTypeID deviceTypeID = new DeviceTypeID(deviceDataModel.getDeviceTypeID());
        DeviceStatus deviceStatus = new DeviceStatus(deviceDataModel.getDeviceStatus());
        DeviceID deviceID = new DeviceID(deviceDataModel.getDeviceID());

        Device device = _deviceFactory.createDevice(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);
        return device;
    }

    /**
     * Converts a list of {@link DeviceDataModel} objects to a list of {@link Device} domain objects.
     *
     * @param deviceDataModels The list of device data models to convert.
     * @return A list containing the corresponding Device domain objects.
     */
    @Override
    public List<Device> toDomain(List<DeviceDataModel> deviceDataModels) {
        if(deviceDataModels == null || deviceDataModels.isEmpty())
            throw new IllegalArgumentException("The list of devices cannot be null or empty.");
        List<Device> listDomain = new ArrayList<>();
        for (DeviceDataModel deviceDataModel : deviceDataModels) {
            Device device = toDomain(deviceDataModel);
            listDomain.add(device);
        }
        return listDomain;
    }
}
