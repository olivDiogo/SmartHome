package smarthome.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.service.IActuatorTypeService;
import smarthome.utils.dto.ActuatorTypeDTO;

@RestController
@RequestMapping("/actuator-type")
public class ActuatorTypeController {

  private final IActuatorTypeService actuatorTypeService;

  private final IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler;

  @Autowired
  public ActuatorTypeController(IActuatorTypeService actuatorTypeService,
      IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler) {
    this.actuatorTypeService = actuatorTypeService;
    this.actuatorTypeAssembler = actuatorTypeAssembler;
  }

  @GetMapping("/all")
  public ResponseEntity<CollectionModel<ActuatorTypeDTO>> getActuatorTypes() {
    List<ActuatorType> actuatorTypeList = actuatorTypeService.getAllActuatorTypes();
    if (actuatorTypeList.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    List<ActuatorTypeDTO> actuatorTypeDTOList = actuatorTypeAssembler.domainToDTO(actuatorTypeList);
    CollectionModel<ActuatorTypeDTO> resource = CollectionModel.of(actuatorTypeDTOList,
        WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ActuatorTypeController.class).getActuatorTypes())
            .withSelfRel());
    return ResponseEntity.ok(resource);
  }
}
