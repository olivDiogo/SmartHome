package smarthome.controller.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.utils.dto.HouseDataDTO;

@SpringBootTest
@AutoConfigureMockMvc
class HouseControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  /**
   * Verify the House is correctly configured when postal code is Portuguese
   */
  @Test
  void shouldReturnHouseDTO_whenHouseIsConfiguredWithPortuguesePostalCode() throws Exception {
    // Arrange
    String street = "Rua de Sao Bento";
    String doorNumber = "123";
    String postalCode = "1200-109";
    String countryCode = "PT";
    double latitude = 38.7143;
    double longitude = -9.1459;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act & Assert
    mockMvc.perform(post("/house/configure")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(houseDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.address").value("Rua de Sao Bento, 123"));
  }

  /**
   * Verify that House is correctly configured when postal code is American
   */
  @Test
  void shouldReturnHouseDTO_whenHouseIsConfiguredWithAmericanPostalCode() throws Exception {
    // Arrange
    String street = "1600 Amphitheatre Parkway";
    String doorNumber = "123";
    String postalCode = "94043";
    String countryCode = "US";
    double latitude = 37.4220;
    double longitude = -122.0841;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act & Assert
    mockMvc.perform(post("/house/configure")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(houseDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.address").value("1600 Amphitheatre Parkway, 123"));
  }

  /**
   * Verify that House is correctly configured when postal code is Spanish
   */
  @Test
  void shouldReturnHouseDTO_whenHouseIsConfiguredWithSpanishPostalCode() throws Exception {
    // Arrange
    String street = "Calle de Alcala";
    String doorNumber = "123";
    String postalCode = "28014";
    String countryCode = "ES";
    double latitude = 40.4189;
    double longitude = -3.6939;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act & Assert
    mockMvc.perform(post("/house/configure")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(houseDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.address").value("Calle de Alcala, 123"));
  }

  /**
   * Verify that House is correctly configured when postal code is Canadian
   */
  @Test
  void shouldReturnHouseDTO_whenHouseIsConfiguredWithCanadianPostalCode() throws Exception {
    // Arrange
    String street = "1455 Boulevard de Maisonneuve O";
    String doorNumber = "123";
    String postalCode = "H3G 1M8";
    String countryCode = "CA";
    double latitude = 45.4972;
    double longitude = -73.5796;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act & Assert
    mockMvc.perform(post("/house/configure")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(houseDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.address").value("1455 Boulevard de Maisonneuve O, 123"));
  }

  /**
   * Verify that House is NOT correctly configured when postal code is invalid
   */
  @Test
  void shouldReturnBadRequest_whenHouseIsConfiguredWithInvalidPostalCode() throws Exception {
    // Arrange
    String street = "Rua de Sao Bento";
    String doorNumber = "123";
    String postalCode = "1200-10";
    String countryCode = "PT";
    double latitude = 38.7143;
    double longitude = -9.1459;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);
    String expectedMessage = "Invalid postal code format";

    // Act & Assert
    mockMvc.perform(post("/house/configure")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(houseDataDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(expectedMessage));
  }

  /**
   * Verify that House is NOT correctly configured when street is blank
   */
  @Test
  void shouldReturnBadRequest_whenHouseIsConfiguredWithBlankStreet() throws Exception {
    // Arrange
    String street = "";
    String doorNumber = "123";
    String postalCode = "1200-109";
    String countryCode = "PT";
    double latitude = 38.7143;
    double longitude = -9.1459;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act & Assert
    mockMvc.perform(post("/house/configure")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(houseDataDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Invalid street"));
  }

  /**
   * Verify that House is NOT correctly configured when door number is blank
   */
  @Test
  void shouldReturnBadRequest_whenHouseIsConfiguredWithBlankDoorNumber() throws Exception {
    // Arrange
    String street = "Rua de Sao Bento";
    String doorNumber = "";
    String postalCode = "1200-109";
    String countryCode = "PT";
    double latitude = 38.7143;
    double longitude = -9.1459;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act & Assert
    mockMvc.perform(post("/house/configure")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(houseDataDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Invalid door number"));
  }

  /**
   * Verify that House is NOT correctly configured when country code is blank
   */
  @Test
  void shouldReturnBadRequest_whenHouseIsConfiguredWithBlankCountryCode() throws Exception {
    // Arrange
    String street = "Rua de Sao Bento";
    String doorNumber = "123";
    String postalCode = "1200-109";
    String countryCode = "";
    double latitude = 38.7143;
    double longitude = -9.1459;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act & Assert
    mockMvc.perform(post("/house/configure")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(houseDataDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Invalid country code"));
  }

  /**
   * Verify that House is NOT correctly configured when latitude is invalid
   */
  @Test
  void shouldReturnBadRequest_whenHouseIsConfiguredWithInvalidLatitude() throws Exception {
    // Arrange
    String street = "Rua de Sao Bento";
    String doorNumber = "123";
    String postalCode = "1200-109";
    String countryCode = "PT";
    double latitude = 91.7143;
    double longitude = -9.1459;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act & Assert
    mockMvc.perform(post("/house/configure")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(houseDataDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Please enter a valid latitude."));
  }

  /**
   * Verify that House is NOT correctly configured when longitude is invalid
   */
  @Test
  void shouldReturnBadRequest_whenHouseIsConfiguredWithInvalidLongitude() throws Exception {
    // Arrange
    String street = "Rua de Sao Bento";
    String doorNumber = "123";
    String postalCode = "1200-109";
    String countryCode = "PT";
    double latitude = 38.7143;
    double longitude = -181.1459;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act & Assert
    mockMvc.perform(post("/house/configure")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(houseDataDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Please enter a valid longitude."));
  }
}