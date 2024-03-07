package SmartHome.domain;

public class SensorType {
    private final String _strDescription;
    private final Unit _unit;

    /**
     * Constructor for the SensorType class.
     *
     * @param strDescription The description of the sensor type.
     * @param unit           The unit of the sensor type.
     * @throws IllegalArgumentException if the description is null or empty, or if the unit is null.
     */
    protected SensorType(String strDescription, Unit unit) {
        this._strDescription = setStrDescription(strDescription);
        this._unit = setUnit(unit);
    }

    /**
     * Sets the description of the sensor type.
     *
     * @param strDescription The description of the sensor type.
     * @return The description of the sensor type.
     * @throws IllegalArgumentException if the description is null or empty.
     */
    private String setStrDescription(String strDescription) throws IllegalArgumentException {
        if (strDescription == null || strDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return strDescription;
    }

    /**
     * Sets the unit of the sensor type.
     *
     * @param unit The unit of the sensor type.
     * @return The unit of the sensor type.
     * @throws IllegalArgumentException if the unit is null.
     */
    private Unit setUnit(Unit unit) throws IllegalArgumentException {
        if (unit == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return unit;
    }

    /**
     * Gets the description of the sensor type.
     *
     * @return The description of the sensor type.
     */
    public String getDescription() {
        return _strDescription;
    }

    /**
     * Gets the unit of the sensor type.
     *
     * @return The unit of the sensor type.
     */
    public Unit getUnit() {
        return _unit;
    }


}
