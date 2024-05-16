package smarthome.controller.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
import smarthome.domain.sensor.ISensor;
import smarthome.domain.service.ISensorService;
import smarthome.domain.value_object.SensorID;
import smarthome.mapper.sensor_vo_assembler.ISensorVOAssembler;
import smarthome.mapper.sensor_vo_assembler.SensorVOAssemblerImpl;
import smarthome.utils.dto.SensorDTO;
import smarthome.utils.dto.data_dto.sensor_data_dto.ISensorDataDTO;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensors")
public class SensorController {

  @NotNull
  private final ISensorService sensorService;
  @NotNull
  private final IAssembler<ISensor, SensorDTO> sensorAssembler;


  /**
   * Constructor for the SensorController
   *
   * @param sensorService   is the service that will handle the sensor
   * @param sensorAssembler is the assembler that will convert the sensor to a DTO
   */
  public SensorController(ISensorService sensorService,
      IAssembler<ISensor, SensorDTO> sensorAssembler) {
    this.sensorService = sensorService;
    this.sensorAssembler = sensorAssembler;
  }

  /**
   * Method to add a sensor to the system
   *
   * @param sensorDataDTO DTO with the sensor data
   * @return ResponseEntity with the sensor data
   */
  @PostMapping
  public ResponseEntity<EntityModel<SensorDTO>> addSensor(
      @RequestBody @Valid ISensorDataDTO sensorDataDTO) {
    ISensorVOAssembler sensorVOAssembler = new SensorVOAssemblerImpl();
    Object[] sensorParameters = sensorVOAssembler.getSensorParameters(sensorDataDTO);

    ISensor sensor = sensorService.addSensor(sensorParameters);

    SensorDTO sensorDTO = sensorAssembler.domainToDTO(sensor);

    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(SensorController.class).addSensor(sensorDataDTO));

    EntityModel<SensorDTO> resource = EntityModel.of(sensorDTO,
        linkToSelf.withSelfRel().withHref("/sensor/" + sensorDTO.sensorID));

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }

  /**
   * Get sensors by ID
   *
   * @param id is the sensorID
   * @return a sensorDTO
   */
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<SensorDTO>> getSensorByID(@PathVariable String id) {
    SensorID sensorID = new SensorID(id);

    Optional<ISensor> sensor = sensorService.getSensorByID(sensorID);

    if (sensor.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    SensorDTO sensorDTO = sensorAssembler.domainToDTO(sensor.get());

    WebMvcLinkBuilder linkToSelf = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(SensorController.class).getSensorByID(sensorDTO.sensorID));

    EntityModel<SensorDTO> resource = EntityModel.of(sensorDTO, linkToSelf.withSelfRel());

    return ResponseEntity.status(HttpStatus.OK).body(resource);
  }

  /**
   * Get a list of all sensors
   *
   * @return a list of sensors
   */
  @GetMapping
  public ResponseEntity<CollectionModel<SensorDTO>> getAllSensors() {
    List<ISensor> sensorList = sensorService.getAllSensors();
    List<SensorDTO> sensorDTOList = sensorAssembler.domainToDTO(sensorList);

    CollectionModel<SensorDTO> resource = CollectionModel.of(
        sensorDTOList, WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(SensorController.class).getAllSensors()).withSelfRel()
    );
    return ResponseEntity.ok(resource);
  }


}
