package smarthome.controller.rest;

import jakarta.validation.constraints.NotNull;
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
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.service.ISensorTypeService;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.dto.SensorTypeDTO;
import smarthome.utils.dto.SensorTypeDataDTO;


@RestController
@RequestMapping("/sensorType")
public class SensorTypeController {

  @NotNull
  private final ISensorTypeService sensorTypeService;
  @NotNull
  private final IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler;

  /**
   * Constructor of the SensorTypeController
   *
   * @param sensorTypeService   the service of the sensor type
   * @param sensorTypeAssembler the assembler of the sensor type
   */
  @Autowired
  public SensorTypeController(ISensorTypeService sensorTypeService,
      IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler) {
    this.sensorTypeService = sensorTypeService;
    this.sensorTypeAssembler = sensorTypeAssembler;
  }

  /**
   * Creates a new sensor type
   *
   * @param sensorTypeDataDTO the data of the sensor type
   * @return the created sensor type
   */
  @PostMapping("/create")
  public ResponseEntity<EntityModel<SensorTypeDTO>> createSensorType(
      @RequestBody SensorTypeDataDTO sensorTypeDataDTO) {
    TypeDescription typeDescription = new TypeDescription(sensorTypeDataDTO.description);
    UnitID unitID = new UnitID(sensorTypeDataDTO.unitID);

    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unitID);
    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(SensorTypeController.class).createSensorType(sensorTypeDataDTO));

    EntityModel<SensorTypeDTO> resource = EntityModel.of(sensorTypeDTO, linkToSelf.withSelfRel());

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }

}
