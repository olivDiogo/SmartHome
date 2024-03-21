package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.HouseDTO;
import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.ZipCode;
import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.domain.House.House;


import java.util.ArrayList;
import java.util.List;

public class HouseAssembler implements Assembler<House, HouseDTO> {
    /**
     * Constructor for the HouseAssembler class.
     */
    public HouseAssembler() {
    }

    /**
     * Method to convert a House into a HouseDTO.
     *
     * @param house is the House to be converted.
     * @return the HouseDTO.
     */
    @Override
    public HouseDTO domainToDTO(final House house) {
        String address = house.getAddress().toString();
        String zipCode = house.getZipCode().toString();
        String gps = house.getGps().toString();

        HouseDTO houseDTO = new HouseDTO(address, zipCode, gps);
        return houseDTO;
    }

    /**
     * Method to convert a list of Houses into a list of HouseDTOs.
     *
     * @param houses is the list of Houses to be converted.
     * @return the list of HouseDTOs.
     */
    @Override
    public List<HouseDTO> domainToDTO(final List<House> houses) {
        List<HouseDTO> housesDTO = new ArrayList<>();

//        houses.forEach(house -> {
//            String address = house.getAddress().toString();
//            String zipCode = house.getZipCode().toString();
//            String gps = house.getGps().toString();
//            HouseDTO houseDTO = new HouseDTO(address, zipCode, gps);
//            housesDTO.add(houseDTO);
//        });
        for (House house : houses) {
            String address = house.getAddress().toString();
            String zipCode = house.getZipCode().toString();
            String gps = house.getGps().toString();
            HouseDTO houseDTO = new HouseDTO(address, zipCode, gps);
            housesDTO.add(houseDTO);
        }
        return housesDTO;
    }


}


