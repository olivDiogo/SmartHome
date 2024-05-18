package smarthome.controller.rest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.service.ISensorModelService;
import smarthome.utils.dto.SensorModelDTO;

@RestController
@RequestMapping("/sensor-model")
public class SensorModelController {

  private final ISensorModelService sensorModelService;

  private final IAssembler<SensorModel, SensorModelDTO> sensorModelAssembler;

  public SensorModelController(ISensorModelService sensorModelService,
      IAssembler<SensorModel, SensorModelDTO> sensorModelAssembler) {
    this.sensorModelService = sensorModelService;
    this.sensorModelAssembler = sensorModelAssembler;
  }

  /**
   * Get all sensor models by sensor type ID.
   *
   * @param sensorTypeID the sensor type ID
   * @return the sensor models by sensor type ID
   */
  @GetMapping(params = "sensorTypeID")
  public ResponseEntity<CollectionModel<SensorModelDTO>> getSensorModelsBySensorTypeId(
      @RequestParam("sensorTypeID") String sensorTypeID) {

    return null;
  }

}
