package smarthome.controller.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.service.IActuatorTypeService;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.dto.ActuatorTypeDTO;
import smarthome.utils.dto.data_dto.ActuatorTypeDataDTO;

@RestController
@RequestMapping("/actuator-types")
public class ActuatorTypeController {

  @NotNull
  private final IActuatorTypeService actuatorTypeService;

  @NotNull
  private final IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler;

  /**
   * Constructor for ActuatorTypeController
   *
   * @param actuatorTypeService   is the service for actuator type
   * @param actuatorTypeAssembler is the assembler for actuator type
   */
  @Autowired
  public ActuatorTypeController(IActuatorTypeService actuatorTypeService,
      IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler) {
    this.actuatorTypeService = actuatorTypeService;
    this.actuatorTypeAssembler = actuatorTypeAssembler;
  }

  /**
   * Get all actuator types
   * @return ResponseEntity<CollectionModel < ActuatorTypeDTO>> is the response entity
   */
  @GetMapping()
  public ResponseEntity<CollectionModel<ActuatorTypeDTO>> getActuatorTypes()
      throws EmptyReturnException {
    List<ActuatorType> actuatorTypeList = actuatorTypeService.getAllActuatorTypes();
    if (actuatorTypeList.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    List<ActuatorTypeDTO> actuatorTypeDTOList = actuatorTypeAssembler.domainToDTO(actuatorTypeList);
    CollectionModel<ActuatorTypeDTO> resource = CollectionModel.of(actuatorTypeDTOList,
        WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ActuatorTypeController.class).getActuatorTypes())
            .withSelfRel());
    return ResponseEntity.ok(resource);
  }

  /**
   * Get an actuator type by ID
   */
  @GetMapping("/{id}")
  public ResponseEntity<ActuatorTypeDTO> getActuatorType(@Valid @PathVariable("id") String id) {
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(id);
    Optional<ActuatorType> actuatorType = actuatorTypeService.getActuatorTypeByID(actuatorTypeID);
    if (actuatorType.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    ActuatorTypeDTO dto = actuatorTypeAssembler.domainToDTO(actuatorType.get());

    // HATEOAS Links
    dto.add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorTypeController.class).getActuatorType(id))
        .withSelfRel());

    return ResponseEntity.ok(dto);
  }

  /**
   * Create an actuator type
   */
  @PostMapping("/add-actuator-type")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ActuatorTypeDTO> addActuatorType(@Valid
  @RequestBody ActuatorTypeDataDTO actuatorTypeDataDTO)
      throws EmptyReturnException {
    TypeDescription typeDescription = new TypeDescription(
        actuatorTypeDataDTO.getActuatorTypeDescription());
    UnitID unitID = new UnitID(actuatorTypeDataDTO.getUnit());
    ActuatorType actuatorType = actuatorTypeService.createActuatorType(typeDescription, unitID);
    actuatorTypeService.addActuatorType(actuatorType);

    ActuatorTypeDTO dto = actuatorTypeAssembler.domainToDTO(actuatorType);

    // HATEOAS Links
    dto.add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorTypeController.class).addActuatorType(
                actuatorTypeDataDTO))
        .withSelfRel());
    dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ActuatorTypeController.class)
        .getActuatorTypes()).withRel("actuator-types"));

    // The client not only receives confirmation that the ActuatorType was created,
    // but also a direct link to access the newly created resource.
    return ResponseEntity.created(
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ActuatorTypeController.class)
            .getActuatorType(actuatorType.getID().getID())).toUri()).body(dto);
  }
}
