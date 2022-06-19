package com.lpnu.iot.parking.structure.parkingfacility;

import com.lpnu.iot.parking.resources.ParkingFacility;
import com.lpnu.iot.parking.structure.CSVRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingFacilityRepository extends CSVRepository<ParkingFacility> {
    @Override
    protected ParkingFacility createNewResource() {
        return new ParkingFacility();
    }

    public ParkingFacilityRepository() {
        super("data/parking_facilities");
    }
}
