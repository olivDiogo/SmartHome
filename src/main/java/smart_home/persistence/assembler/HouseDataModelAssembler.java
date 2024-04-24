package smart_home.persistence.assembler;

import smart_home.domain.house.House;
import smart_home.domain.house.IHouseFactory;
import smart_home.persistence.jpa.data_model.HouseDataModel;
import smart_home.value_object.Address;
import smart_home.value_object.GPS;
import smart_home.value_object.HouseID;
import smart_home.value_object.PostalCodeFactory;

import java.util.ArrayList;
import java.util.List;

public class HouseDataModelAssembler implements IDataModelAssembler<HouseDataModel, House>{
    private IHouseFactory houseFactory;

    public HouseDataModelAssembler(IHouseFactory houseFactory) {
    }
    @Override
    public House toDomain(HouseDataModel houseDataModel) {
        GPS gps = new GPS(houseDataModel.getLatitude(), houseDataModel.getLongitude());
        Address address = new Address(houseDataModel.getStreet(), houseDataModel.getDoorNumber(), houseDataModel.getPostalCode(), houseDataModel.getCountryCode(), new PostalCodeFactory());
        HouseID houseID = new HouseID(houseDataModel.getHouseID());

        House house = houseFactory.createHouse(houseID, address, gps);
        return house;    }

    @Override
    public List<House> toDomain(List<HouseDataModel> houseDataModels) {
        List <House> houses = new ArrayList<>();
        for (HouseDataModel houseDataModel : houseDataModels) {
            House house = toDomain(houseDataModel);
            houses.add(house);
        }
        return houses;
    }
}
