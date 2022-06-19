package com.lpnu.iot.parking.structure.parking_facility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ParkingFacilityController {

    @Autowired
    ParkingFacilityService parkingFacilityService;
}
