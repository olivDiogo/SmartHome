package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.MeasurementTypeDTO;
import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.domain.MeasurementType.MeasurementType;

import java.util.ArrayList;
import java.util.List;

/**
 * Assembler class for converting {@link MeasurementType} domain entities to {@link MeasurementTypeDTO} data transfer objects.
 */

public class MeasurementTypeAssembler implements Assembler<MeasurementType, MeasurementTypeDTO> {
    /**
     * Constructor of the MeasurementTypeAssembler class.
     */
    public MeasurementTypeAssembler() {

    }

    /**
     * Converts a {@link MeasurementType} domain entity to a {@link MeasurementTypeDTO} data transfer object.
     *
     * @param measurementType is the domain entity to be converted.
     * @return The {@link MeasurementTypeDTO} data transfer object.
     */
    @Override
    public MeasurementTypeDTO domainToDTO(MeasurementType measurementType) {
        if (measurementType == null) {
            throw new IllegalArgumentException("The MeasurementType cannot be null.");
        }
        String measurementID = measurementType.getID().toString();
        String measurementTypeDescription = measurementType.getUnitDescription().toString();

        return new MeasurementTypeDTO(measurementID, measurementTypeDescription);
    }

    /**
     * Converts a list of {@link MeasurementType} domain entities to a list of {@link MeasurementTypeDTO} data transfer objects.
     *
     * @param measurementTypes is the list of domain entities to be converted.
     * @return
     */

    @Override
    public List<MeasurementTypeDTO> domainToDTO(List<MeasurementType> measurementTypes) {
        if (measurementTypes == null) {
            throw new IllegalArgumentException("The list of MeasurementTypes cannot be null.");
        }
        List<MeasurementTypeDTO> measurementTypeDTOS = new ArrayList<>();
        for (MeasurementType measurementType : measurementTypes) {
            MeasurementTypeDTO measurementTypeDTO = domainToDTO(measurementType);
            measurementTypeDTOS.add(measurementTypeDTO);
        }
        return List.copyOf(measurementTypeDTOS);
    }


}
