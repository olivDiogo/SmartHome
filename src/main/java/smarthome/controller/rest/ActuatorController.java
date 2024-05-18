package smarthome.controller.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.service.IActuatorService;
import smarthome.domain.value_object.ActuatorID;
import smarthome.mapper.actuator_vo_assembler.ActuatorVOAssemblerImpl;
import smarthome.mapper.actuator_vo_assembler.IActuatorVOAssembler;
import smarthome.utils.dto.ActuatorDTO;
import smarthome.utils.dto.data_dto.actuator_data_dto.IActuatorDataDTO;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actuators")
public class ActuatorController {

  private final IActuatorService actuatorService;
  private final IAssembler<IActuator, ActuatorDTO> actuatorAssembler;

  /**
   * Constructor
   *
   * @param actuatorService   is the service for the Actuator.
   * @param actuatorAssembler is the assembler for the Actuator.
   */
  @Autowired
  public ActuatorController(IActuatorService actuatorService,
      IAssembler<IActuator, ActuatorDTO> actuatorAssembler) {
    this.actuatorService = actuatorService;
    this.actuatorAssembler = actuatorAssembler;
  }

  /**
   * Method to add an Actuator to a Device.
   *
   * @param actuatorDataDTO is the Actuator data.
   * @return the Actuator data transfer object.
   */
  @PostMapping
  public ResponseEntity<EntityModel<ActuatorDTO>> addActuator(
      @RequestBody @Valid IActuatorDataDTO actuatorDataDTO) {
    IActuatorVOAssembler actuatorVOAssembler = new ActuatorVOAssemblerImpl();
    Object[] actuatorParameters = actuatorVOAssembler.getActuatorParameters(actuatorDataDTO);

    IActuator actuator = actuatorService.addActuator(actuatorParameters);
    ActuatorDTO actuatorDTO = actuatorAssembler.domainToDTO(actuator);

    // Create link to self
    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ActuatorController.class).addActuator(actuatorDataDTO));

    // Create link to get the Actuator by its ID
    WebMvcLinkBuilder linkToActuator = WebMvcLinkBuilder
        .linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorController.class).getActuatorByID(actuatorDTO.id));

    EntityModel<ActuatorDTO> resource = EntityModel.of(actuatorDTO, linkToSelf.withSelfRel().withRel("add-actuator"),
        linkToActuator.withRel("get-added-actuator"));

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }

  /**
   * Method to get all Actuators.
   *
   * @return a collection of Actuator data transfer objects.
   */
  @GetMapping
  public ResponseEntity<List<EntityModel<ActuatorDTO>>> getAllActuators() {
    List<IActuator> actuators = actuatorService.getAllActuators();
    List<ActuatorDTO> actuatorDTOs = actuatorAssembler.domainToDTO(actuators);
    List<EntityModel<ActuatorDTO>> resources = new java.util.ArrayList<>(List.of());

    // Create link to self
    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ActuatorController.class).getAllActuators());

    for (ActuatorDTO actuatorDTO : actuatorDTOs) {
      WebMvcLinkBuilder linkToActuator = WebMvcLinkBuilder
          .linkTo(WebMvcLinkBuilder.methodOn(ActuatorController.class).getActuatorByID(actuatorDTO.id));

      EntityModel<ActuatorDTO> resource = EntityModel.of(
          actuatorDTO,
          linkToSelf.withSelfRel().withRel("get-all-actuators"),
          linkToActuator.withRel("get-actuator-by-id")
      );

      resources.add(resource);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(resources);
  }

  /**
   * Method to get an Actuator by its ID.
   *
   * @param id is the Actuator ID.
   * @return the Actuator data transfer object.
   */
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<ActuatorDTO>> getActuatorByID (@PathVariable ("id") String id) {
    Optional<IActuator> actuator = actuatorService.getActuatorByID(new ActuatorID(id));

    if (actuator.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    ActuatorDTO actuatorDTO = actuatorAssembler.domainToDTO(actuator.get());

    // Create link to self
    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ActuatorController.class).getActuatorByID(id));

    // Create link to get all Actuators
    WebMvcLinkBuilder linkToActuators = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ActuatorController.class).getAllActuators());

    EntityModel<ActuatorDTO> resource = EntityModel.of(actuatorDTO, linkToSelf.withSelfRel().withRel("get-actuator-by-id"), linkToActuators.withRel("get-actuators"));

    return ResponseEntity.status(HttpStatus.OK).body(resource);
  }
}
