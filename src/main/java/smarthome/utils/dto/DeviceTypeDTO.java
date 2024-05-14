package smarthome.utils.dto;

import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import smarthome.ddd.IDTO;

@EqualsAndHashCode
public class DeviceTypeDTO extends RepresentationModel<DeviceTypeDTO> implements IDTO {

  public final String description;

  /**
   * Constructs a new DeviceTypeDTO object.
   *
   * @param description The description of the device type.
   */
  public DeviceTypeDTO(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
