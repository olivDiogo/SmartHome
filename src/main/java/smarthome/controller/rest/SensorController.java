package smarthome.controller.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.service.ISensorService;
import smarthome.mapper.sensor_vo_assembler.ISensorVOAssembler;
import smarthome.mapper.sensor_vo_assembler.SensorVOAssemblerImpl;
import smarthome.utils.dto.SensorDTO;
import smarthome.utils.dto.sensor_data_dto.ISensorDataDTO;

@RestController
@RequestMapping("/sensor")
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
  @PostMapping("/add")
  public ResponseEntity<SensorDTO> addSensor(@RequestBody ISensorDataDTO sensorDataDTO) {
    ISensorVOAssembler sensorVOAssembler = new SensorVOAssemblerImpl();
    Object[] sensorParameters = sensorVOAssembler.getSensorParameters(sensorDataDTO);

    ISensor sensor = sensorService.addSensor(sensorParameters);

    SensorDTO sensorDTO = sensorAssembler.domainToDTO(sensor);

    return ResponseEntity.status(HttpStatus.CREATED).body(sensorDTO);
  }


}
