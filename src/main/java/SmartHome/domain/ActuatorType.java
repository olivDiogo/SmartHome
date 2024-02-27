package SmartHome.domain;

public class ActuatorType {
    private String _strDescription;
    private Unit _unit;

    /**
     * Constructor for the ActuatorType class
     *
     * @param strDescription The description of the actuator type
     * @param unit The unit of the actuator type
     */
    protected ActuatorType( String strDescription, Unit unit ) {
            setDescription(strDescription);
            setUnit(unit);
    }

    /**
     * Setter for the description of the actuator type
     *
     * @param strDescription The description of the actuator type
     */
    private void setDescription(String strDescription) {
        if( strDescription == null || strDescription.trim().isEmpty() ) {
            throw new IllegalArgumentException("Please enter a valid description for the actuator type.");
        }
        this._strDescription = strDescription;
    }

    /**
     * Setter for the unit of the actuator type
     *
     * @param unit The unit of the actuator type
     */
    private void setUnit(Unit unit) {
        if( unit == null ) {
            throw new IllegalArgumentException("Please enter a valid unit for the actuator type.");
        }
        this._unit = unit;
    }


}
