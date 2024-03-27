package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

public class MeasurementTypeDTO implements DTO {

    public final String measurementID;
    public final String description;

    /**
     * Constructor for the MeasurementDTO class.
     *
     * @param measurementID is the ID of the Measurement.
     * @param measurementTypeDescription is the description of the Measurement.
     */
    public MeasurementTypeDTO(String measurementID, String measurementTypeDescription) {

        this.measurementID = measurementID;
        this.description = measurementTypeDescription;
    }

    @Override
    public String toString() {
        return "MeasurementTypeDTO{" +
                "measurementID='" + measurementID + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
