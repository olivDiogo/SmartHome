package SmartHome.domain;

public class ActuatorType {
    private String _strDescription;

    /**
     * Constructor for the ActuatorType class
     *
     * @param strDescription The description of the actuator type
     */
    protected ActuatorType( String strDescription) {
            setDescription(strDescription);
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
     * Getter for the description of the actuator type
     *
     * @return The description of the actuator type
     */
    public String getDescription() {
        return _strDescription;
    }




}
