/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper.sensor_vo_assembler;

import smarthome.utils.dto.data_dto.sensor_data_dto.ISensorDataDTO;

public interface ISensorVOAssembler {

  Object[] getSensorParameters(ISensorDataDTO sensorDataDTO);
}
