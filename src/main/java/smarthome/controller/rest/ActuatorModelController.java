package smarthome.controller.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.service.IActuatorModelService;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.dto.ActuatorModelDTO;
import smarthome.utils.dto.actuator_data_dto.ActuatorDataGenericDTOImp;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/actuator-model")
public class ActuatorModelController {

  private final IActuatorModelService actuatorModelService;
  private final IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler;

  @Autowired
  public ActuatorModelController(
      IActuatorModelService actuatorModelService,
      IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler) {
    this.actuatorModelService = actuatorModelService;
    this.actuatorModelAssembler = actuatorModelAssembler;
  }

//  @GetMapping("/all")
//  public ResponseEntity<List<ActuatorModelDTO>> getAllActuatorModels() {
//
//    List<ActuatorModel> actuatorModels = actuatorModelService.getAllActuatorModels();
//    List<ActuatorModelDTO> actuatorModelDTOS = actuatorModelAssembler.domainToDTO(actuatorModels);
//    return ResponseEntity.status(HttpStatus.OK).body(actuatorModelDTOS);
//  }
//
//  @GetMapping("/get")
//  public ResponseEntity<ActuatorModelDTO> getActuatorModel(
//      @Valid @RequestBody ActuatorDataGenericDTOImp actuatorDataDTO) {
//
//    ModelPath modelPath = new ModelPath(actuatorDataDTO.actuatorModelPath);
//
//    ActuatorModel actuatorModel = actuatorModelService.getActuatorModel(modelPath).get();
//    ActuatorModelDTO actuatorModelDTO = actuatorModelAssembler.domainToDTO(actuatorModel);
//    return ResponseEntity.status(HttpStatus.OK).body(actuatorModelDTO);
//  }

  @GetMapping("/{actuatorTypeID}")
public ResponseEntity<CollectionModel<ActuatorModelDTO>> getActuatorModelsByActuatorTypeId(
    @PathVariable String actuatorTypeID) {

    ActuatorTypeID actuatorTypeIDObj = new ActuatorTypeID(actuatorTypeID);

    List<ActuatorModel> actuatorModels =
        actuatorModelService.getActuatorModelsByActuatorTypeId(actuatorTypeIDObj);
    List<ActuatorModelDTO> actuatorModelDTOS = actuatorModelAssembler.domainToDTO(actuatorModels);
    CollectionModel<ActuatorModelDTO> resource = CollectionModel.of(actuatorModelDTOS, WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorModelController.class).getActuatorModelsByActuatorTypeId(actuatorTypeID))
        .withSelfRel());
    return ResponseEntity.ok(resource);
}
}
