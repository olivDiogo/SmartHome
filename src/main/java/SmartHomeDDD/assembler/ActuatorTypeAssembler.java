package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.ActuatorTypeDTO;
import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.List;

public class ActuatorTypeAssembler implements Assembler<ActuatorType, ActuatorTypeDTO> {

    /**
     * Constructor of the ActuatorTypeAssembler class.
     */
    public ActuatorTypeAssembler () {

    }

    /**
     * Converts an ActuatorType domain entity to an ActuatorTypeDTO data transfer object.
     * @param actuatorType is the domain entity to be converted.
     * @return The ActuatorTypeDTO data transfer object.
     */
    @Override
    public ActuatorTypeDTO domainToDTO(ActuatorType actuatorType) {
        String actuatorTypeDescription = actuatorTypeDescriptionToString (actuatorType.getID());

        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeDescription);
        return actuatorTypeDTO;
    }


    /**
     * Converts a list of ActuatorType domain entities to a list of ActuatorTypeDTO data transfer objects.
     * @param actuatorTypes is the list of domain entities to be converted.
     * @return The list of ActuatorTypeDTO data transfer objects.
     */
    @Override
    public List<ActuatorTypeDTO> domainToDTO(List<ActuatorType> actuatorTypes) {

        List<ActuatorTypeDTO> actuatorTypeDTOS = new ArrayList<>();

        for (ActuatorType actuatorType : actuatorTypes){
            String actuatorTypeDescription = actuatorType.getID().toString();

            ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeDescription);
            actuatorTypeDTOS.add(actuatorTypeDTO);
        }
        return actuatorTypeDTOS;
    }

    /**
     * Converts an ActuatorTypeDTO data transfer object to an ActuatorType domain entity.
     * @param typeDescription is the data transfer object to be converted.
     * @return The ActuatorType domain entity.
     */
    public String actuatorTypeDescriptionToString (TypeDescription typeDescription){
        return typeDescription.toString();
    }



}
