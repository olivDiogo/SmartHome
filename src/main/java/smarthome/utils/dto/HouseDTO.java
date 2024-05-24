package smarthome.utils.dto;


import org.springframework.hateoas.RepresentationModel;
import smarthome.ddd.IDTO;

public class HouseDTO extends RepresentationModel<ActuatorTypeDTO> implements IDTO {

  public final String address;
  public final String gps;
  public final String houseID;

  /**
   * Constructor for the HouseDTO class.
   *
   * @param address is the address of the House.
   * @param gps     is the GPS coordinates of the House.
   */
  public HouseDTO(String address, String gps, String houseID) {
    this.address = address;
    this.gps = gps;
    this.houseID = houseID;
  }

  @Override
  public String toString() {
    return address + " " + gps + " " + houseID;
  }
}

