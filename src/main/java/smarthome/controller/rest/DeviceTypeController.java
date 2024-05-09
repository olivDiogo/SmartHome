package smarthome.controller.rest;


import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.service.IDeviceTypeService;
import smarthome.domain.value_object.TypeDescription;
import smarthome.utils.dto.DeviceTypeDTO;

@RestController
@RequestMapping("/deviceType")
public class DeviceTypeController {

  @NotBlank
  private final IDeviceTypeService deviceTypeService;

  @NotBlank
  private final IAssembler<DeviceType, DeviceTypeDTO> deviceTypeAssembler;


  /**
   * Constructor for DeviceTypeContoller
   */
  @Autowired
  public DeviceTypeController(IDeviceTypeService deviceTypeService,
      IAssembler<DeviceType, DeviceTypeDTO> deviceTypeAssembler) {
    this.deviceTypeService = deviceTypeService;
    this.deviceTypeAssembler = deviceTypeAssembler;
  }


  /**
   * Adds a new device type to the system.
   *
   * @return The device type that was added.
   */
  @PostMapping("/add")
  public ResponseEntity<EntityModel<DeviceTypeDTO>> addDeviceType(
      @RequestBody String typeDescription) {

    TypeDescription description = new TypeDescription(typeDescription);

    DeviceType deviceType = deviceTypeService.addDeviceType(description);
    DeviceTypeDTO deviceTypeDTO = deviceTypeAssembler.domainToDTO(deviceType);

    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder
        .linkTo(
            WebMvcLinkBuilder.methodOn(DeviceTypeController.class).addDeviceType(typeDescription));

    EntityModel<DeviceTypeDTO> resource = EntityModel.of(deviceTypeDTO, linkToSelf.withSelfRel());

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);

  }

}

