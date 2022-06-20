package com.lpnu.iot.parking.structure.parkingfacility;

import com.lpnu.iot.parking.resources.ParkingFacility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ParkingFacilityService {

    @Autowired
    private ParkingFacilityRepository parkingFacilityRepository;


    public Map<Long, ParkingFacility> getParkingFacilities(Long shopId) {
        return parkingFacilityRepository.findAll(parkingFacility ->
                parkingFacility.getBelongingShopId().equals(shopId));
    }

    public ParkingFacility getParkingFacility(Long facilityId) {
        return parkingFacilityRepository.findById(facilityId);
    }

    public ParkingFacility addParkingFacility(Long shopId, String address)  throws Exception {
        var added = parkingFacilityRepository.add(new ParkingFacility(shopId, address, 0));

        parkingFacilityRepository.writeDataToFile();

        return added;
    }

}
