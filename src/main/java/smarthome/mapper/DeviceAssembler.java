package smarthome.mapper;

import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.utils.Validator;
import smarthome.utils.dto.DeviceDTO;

public class DeviceAssembler implements IAssembler<Device, DeviceDTO> {

  /**
   * Converts a Device object to a DeviceDTO object.
   *
   * @param domainEntity The Device object to be converted.
   * @return The DeviceDTO object.
   */
  @Override
  public DeviceDTO domainToDTO(Device domainEntity) {
    Validator.validateNotNull(domainEntity, "Device");

    String deviceID = domainEntity.getID().toString();
    String roomID = domainEntity.getRoomID().toString();
    String deviceName = domainEntity.getDeviceName().toString();
    String deviceStatus = domainEntity.getDeviceStatus().toString();

    return new DeviceDTO(deviceID, roomID, deviceName, deviceStatus);
  }

  /**
   * Converts a list of Device objects to a list of DeviceDTO objects.
   *
   * @param domainEntities The list of Device objects to be converted.
   * @return The list of DeviceDTO objects.
   */
  @Override
  public List<DeviceDTO> domainToDTO(List<Device> domainEntities) {
    if (domainEntities == null || domainEntities.isEmpty()) {
      throw new IllegalArgumentException("The list of Devices cannot be null or empty.");
    }
    return domainEntities.stream().map(this::domainToDTO).toList();
  }
}
