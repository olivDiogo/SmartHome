package SmartHome.domain;

public class SensorType
{
    private final String _strDescription;
    private final Unit _unit;

    protected SensorType( String strDescription, Unit unit )
    {
        this._strDescription = setStrDescription(strDescription);
        this._unit = setUnit(unit);
    }

    private String setStrDescription( String strDescription ) throws IllegalArgumentException
    {
        if (strDescription == null || strDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return strDescription;
    }

    private Unit setUnit( Unit unit ) throws IllegalArgumentException
    {
        if (unit == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return unit;
    }

    public String getDescription() {
        return _strDescription;
    }

    public Unit getUnit() {
        return _unit;
    }


}
