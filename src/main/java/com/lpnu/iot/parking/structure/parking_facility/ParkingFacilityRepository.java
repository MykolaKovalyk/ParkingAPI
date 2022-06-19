package com.lpnu.iot.parking.structure.parking_facility;

import com.lpnu.iot.parking.resources.ParkingFacility;
import com.lpnu.iot.parking.structure.CSVRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingFacilityRepository extends CSVRepository<ParkingFacility> {
    @Override
    protected ParkingFacility createNewResource() {
        return new ParkingFacility();
    }
}
