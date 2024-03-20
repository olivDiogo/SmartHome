package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.HousesDTO;
import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.ZipCode;
import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.domain.House.House;


import java.util.ArrayList;
import java.util.List;

public class HouseAssembler implements Assembler<House, HousesDTO> {
    ;

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
    public HousesDTO domainToDTO(House house) {
        String zipCode = zipCodeToString(house.getZipCode());
        String address = addressToString(house.getAddress());
        String gps = gpsToString(house.getGps());

        HousesDTO houseDTO = new HousesDTO(address, zipCode, gps);
        return houseDTO;
    }

    /**
     * Method to convert a list of Houses into a list of HouseDTOs.
     *
     * @param houses is the list of Houses to be converted.
     * @return the list of HouseDTOs.
     */
    @Override
    public List<HousesDTO> domainToDTO(List<House> houses) {
        List<HousesDTO> housesDTO = new ArrayList<>();
        for (House house : houses) {
            String address = zipCodeToString(house.getZipCode());
            String zipCode = addressToString(house.getAddress());
            String gps = gpsToString(house.getGps());
            HousesDTO houseDTO = new HousesDTO(address, zipCode, gps);
            housesDTO.add(houseDTO);
        }
        return housesDTO;
    }


    /**
     * Method to obtain the zip-code of the House as a String.
     *
     * @param zipCode is the zip-code of the House.
     * @return the zip-code of the House.
     */
    private String zipCodeToString(ZipCode zipCode) {
        return String.valueOf(zipCode);
    }

    /**
     * Method to obtain the address of the House as a String.
     *
     * @param address is the address of the House.
     * @return the address of the House.
     */
    private String addressToString(Address address) {
        return String.valueOf(address);
    }

    /**
     * Method to obtain the GPS coordinates of the House as a String.
     *
     * @param gps is the GPS coordinates of the House.
     * @return the GPS coordinates of the House.
     */
    private String gpsToString(GPS gps) {
        return String.valueOf(gps);
    }




}


