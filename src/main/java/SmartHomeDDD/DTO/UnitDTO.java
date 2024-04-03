package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

public class UnitDTO implements DTO {

    public final String unitID;
    public final String description;
    public final String unitSymbol;

    /**
     * Constructor for the MeasurementDTO class.
     *
     * @param unitID is the ID of the Measurement.
     * @param description is the description of the Measurement.
     */
    public UnitDTO(String unitID, String description, String unitSymbol) {

        this.unitID = unitID;
        this.description = description;
        this.unitSymbol = unitSymbol;
    }

    @Override
    public String toString() {
        return "UnitDTO{" +
                "unitID='" + unitID + '\'' +
                ", description='" + description + '\'' +
                ", unitSymbol='" + unitSymbol +
                '}';
    }
}
