package smarthome.utils.dto;

import jakarta.validation.constraints.NotBlank;

public class UnitDataDTO {


  @NotBlank (message = "Description cannot be empty")
  public String description;

  @NotBlank (message = "UnitSymbol cannot be empty")
  public String unitSymbol;

  /**
   * Constructor for the UnitDataDTO.
   *
   * @param description the description of the unit.
   * @param unitSymbol  the symbol of the unit.
   */
  public UnitDataDTO(String description, String unitSymbol) {
    this.description = description;
    this.unitSymbol = unitSymbol;
  }

}
