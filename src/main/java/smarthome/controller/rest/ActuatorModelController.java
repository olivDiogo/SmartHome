package smarthome.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.service.IActuatorModelService;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.utils.dto.ActuatorModelDTO;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/actuator-model")
public class ActuatorModelController {

  private final IActuatorModelService actuatorModelService;
  private final IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler;

  /**
   * Instantiates a new Actuator model controller.
   *
   * @param actuatorModelService the actuator model service
   * @param actuatorModelAssembler the actuator model assembler
   */
  @Autowired
  public ActuatorModelController(
      IActuatorModelService actuatorModelService,
      IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler) {
    this.actuatorModelService = actuatorModelService;
    this.actuatorModelAssembler = actuatorModelAssembler;
  }

  /**
   * Get all actuator models by actuator type ID.
   *
   * @param actuatorTypeID the actuator type ID
   * @return the actuator models by actuator type ID
   */
  @GetMapping("/{actuatorTypeID}")
public ResponseEntity<CollectionModel<ActuatorModelDTO>> getActuatorModelsByActuatorTypeId(
    @PathVariable String actuatorTypeID) throws EmptyReturnException {

    ActuatorTypeID actuatorTypeIDObj = new ActuatorTypeID(actuatorTypeID);

    List<ActuatorModel> actuatorModels =
        actuatorModelService.getActuatorModelsByActuatorTypeId(actuatorTypeIDObj);

    if (actuatorModels.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    List<ActuatorModelDTO> actuatorModelDTOS = actuatorModelAssembler.domainToDTO(actuatorModels);
    CollectionModel<ActuatorModelDTO> resource = CollectionModel.of(actuatorModelDTOS, linkTo(methodOn(ActuatorModelController.class).getActuatorModelsByActuatorTypeId(actuatorTypeID))
        .withSelfRel());
    return ResponseEntity.ok(resource);
}

}
