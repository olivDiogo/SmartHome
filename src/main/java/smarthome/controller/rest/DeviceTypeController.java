package smarthome.controller.rest;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.service.IDeviceTypeService;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.utils.dto.DeviceTypeDTO;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/device-types")
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
  @PostMapping("/")
  public ResponseEntity<EntityModel<DeviceTypeDTO>> addDeviceType(
      @RequestBody @Valid String typeDescription) {

    TypeDescription description = new TypeDescription(typeDescription);

    DeviceType deviceType = deviceTypeService.addDeviceType(description);
    DeviceTypeDTO deviceTypeDTO = deviceTypeAssembler.domainToDTO(deviceType);

    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder
        .linkTo(
            WebMvcLinkBuilder.methodOn(DeviceTypeController.class).addDeviceType(typeDescription));

    EntityModel<DeviceTypeDTO> resource = EntityModel.of(deviceTypeDTO, linkToSelf.withSelfRel());

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);

  }


  /**
   * Gets all device types in the system.
   *
   * @return A list of all device types in the system.
   */
  @GetMapping
  public ResponseEntity<CollectionModel<DeviceTypeDTO>> getAllDeviceTypes() throws EmptyReturnException {
    List<DeviceType> deviceTypes = deviceTypeService.getAllDeviceTypes();
    if (deviceTypes.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    List<DeviceTypeDTO> deviceTypeDTOs = deviceTypeAssembler.domainToDTO(deviceTypes);
    CollectionModel<DeviceTypeDTO> resource = CollectionModel.of(deviceTypeDTOs);

    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(DeviceTypeController.class).getAllDeviceTypes());

    resource.add(linkToSelf.withSelfRel());

    return ResponseEntity.ok(resource);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DeviceTypeDTO> getDeviceTypeById(@Valid @PathVariable("id") String id) {

    DeviceTypeID deviceTypeID = new DeviceTypeID(id);
    Optional<DeviceType> deviceType = deviceTypeService.getDeviceTypeByID(deviceTypeID);

    if (deviceType.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    DeviceTypeDTO deviceTypeDTO = deviceTypeAssembler.domainToDTO(deviceType.get());

    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(DeviceTypeController.class).getDeviceTypeById(id));

    deviceTypeDTO.add(linkToSelf.withSelfRel());

    return ResponseEntity.ok(deviceTypeDTO);
  }

}

