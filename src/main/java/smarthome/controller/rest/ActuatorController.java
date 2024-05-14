package smarthome.controller.rest;

import jakarta.validation.Valid;
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
import smarthome.domain.actuator.IActuator;
import smarthome.domain.service.IActuatorService;
import smarthome.mapper.actuator_vo_assembler.ActuatorVOAssemblerImpl;
import smarthome.mapper.actuator_vo_assembler.IActuatorVOAssembler;
import smarthome.utils.dto.ActuatorDTO;
import smarthome.utils.dto.data_dto.actuator_data_dto.IActuatorDataDTO;

@RestController
@RequestMapping("/actuator")
public class ActuatorController {

  private final IActuatorService actuatorService;
  private final IAssembler<IActuator, ActuatorDTO> actuatorAssembler;

  /**
   * Constructor
   *
   * @param actuatorService is the service for the Actuator.
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
  @PostMapping("/")
  public ResponseEntity<EntityModel<ActuatorDTO>> addActuator(@RequestBody @Valid IActuatorDataDTO actuatorDataDTO) {
    IActuatorVOAssembler actuatorVOAssembler = new ActuatorVOAssemblerImpl();
    Object[] actuatorParameters = actuatorVOAssembler.getActuatorParameters(actuatorDataDTO);

    IActuator actuator = actuatorService.addActuator(actuatorParameters);
    ActuatorDTO actuatorDTO = actuatorAssembler.domainToDTO(actuator);

    // Create link to self
    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ActuatorController.class).addActuator(actuatorDataDTO));

    EntityModel<ActuatorDTO> resource = EntityModel.of(actuatorDTO, linkToSelf.withSelfRel());

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }
}
