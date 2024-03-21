package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

public class MeasurementDTO implements DTO {

    public final String measurementID;

    /**
     * Constructor for the MeasurementDTO class.
     *
     * @param measurementID is the ID of the Measurement.
     */
    public MeasurementDTO(String measurementID) {
        this.measurementID = measurementID;
    }

    /**
     * Getter for the Measurement ID.
     *
     * @return the Measurement ID.
     */
    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "measurementID='" + measurementID + '\'' +
                '}';
    }
}
