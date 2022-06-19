package com.lpnu.iot.parking.structure.parking_facility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingFacilityService {

    @Autowired
    ParkingFacilityRepository parkingFacilityRepository;
}
