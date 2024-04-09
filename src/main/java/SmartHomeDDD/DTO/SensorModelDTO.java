package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Represents a Data Transfer Object (DTO) for a sensor model.
 */
public final class SensorModelDTO implements DTO {
    /**
     * The unique identifier of the sensor model.
     */
    public final String sensorModelID;

    /**
     * The name of the sensor model.
     */
    public final String sensorModelName;

    /**
     * The file path to the sensor model's data.
     */
    public final String _modelPath;

    /**
     * Constructs a new {@code SensorModelDTO} with the specified ID, name, and model path.
     *
     * @param sensorModelID   the unique identifier of the sensor model; should not be {@code null}.
     * @param sensorModelName the name of the sensor model; should not be {@code null}.
     * @param modelPath       the file path to the sensor model's data; should not be {@code null}.
     */
    public SensorModelDTO(String sensorModelID, String sensorModelName, String modelPath) {
        this.sensorModelID = sensorModelID;
        this.sensorModelName = sensorModelName;
        this._modelPath = modelPath;
    }

    @Override
    public String toString() {
        return sensorModelID + " " + sensorModelName + " " + _modelPath;
    }
}
