package smarthome.persistence.assembler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.persistence.jpa.data_model.HouseDataModel;
import smarthome.utils.Validator;

@Component
public class HouseDataModelAssembler implements IDataModelAssembler<HouseDataModel, House> {

  private final IHouseFactory houseFactory;

  public HouseDataModelAssembler(IHouseFactory houseFactory) {
    Validator.validateNotNull(houseFactory, "House Factory");

    this.houseFactory = houseFactory;
  }

  @Override
  public House toDomain(HouseDataModel houseDataModel) {
    GPS gps = new GPS(houseDataModel.getLatitude(), houseDataModel.getLongitude());
    Address address = new Address(houseDataModel.getStreet(), houseDataModel.getDoorNumber(),
        houseDataModel.getPostalCode(), houseDataModel.getCountryCode(), new PostalCodeFactory());
    HouseID houseID = new HouseID(houseDataModel.getHouseID());

    return houseFactory.createHouse(houseID, address, gps);
  }

  @Override
  public List<House> toDomain(List<HouseDataModel> houseDataModels) {
    List<House> houses = new ArrayList<>();
    for (HouseDataModel houseDataModel : houseDataModels) {
      House house = toDomain(houseDataModel);
      houses.add(house);
    }
    return houses;
  }
}
