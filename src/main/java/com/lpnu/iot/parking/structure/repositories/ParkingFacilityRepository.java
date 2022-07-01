package com.lpnu.iot.parking.structure.repositories;

import com.lpnu.iot.parking.resources.ParkingFacility;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingFacilityRepository extends CSVRepository<ParkingFacility> {
    public ParkingFacilityRepository() {
        super("data/parking_facilities");
    }

    @Override
    protected ParkingFacility createNewResource() {
        return new ParkingFacility();
    }
}
