package com.lpnu.iot.parking.structure.parking_facility;

import com.lpnu.iot.parking.resources.ParkingFacility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Service
public class ParkingFacilityService {

    @Autowired
    ParkingFacilityRepository parkingFacilityRepository;


    public Map<Long, ParkingFacility> getParkingFacilities(Long shopId) {
        return parkingFacilityRepository.findAll(parkingFacility ->
                parkingFacility.belongingShopId.equals(shopId));
    }

    public ParkingFacility getParkingFacility(Long facilityId) {
        return parkingFacilityRepository.findById(facilityId);
    }

    public ParkingFacility addParkingFacility(Long shopId) {
        return parkingFacilityRepository.save(new ParkingFacility(shopId));
    }

}
