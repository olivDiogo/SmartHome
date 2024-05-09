/**
 * This class represents the data transfer object for the house data
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HouseDataDTO {

  @NotBlank(message = "Street cannot be empty")
  public final String street;

  @NotBlank(message = "Door number cannot be empty")
  public final String doorNumber;

  @NotBlank(message = "Postal code cannot be empty")
  public final String postalCode;

  @NotBlank(message = "Country code cannot be empty")
  public final String countryCode;

  public final double latitude;

  public final double longitude;
}

