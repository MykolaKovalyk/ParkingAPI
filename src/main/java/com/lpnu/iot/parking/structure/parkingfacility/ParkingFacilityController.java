package com.lpnu.iot.parking.structure.parkingfacility;

import com.lpnu.iot.parking.resources.ParkingFacility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RestController
public class ParkingFacilityController {

    @Autowired
    private ParkingFacilityService parkingFacilityService;



    @GetMapping(path =
    "shops/{shopId}/parking-facilities")
    public Map<Long, ParkingFacility> getParkingFacilities(
            @PathVariable Long shopId
    ) {
        return parkingFacilityService.getParkingFacilities(shopId);
    }

    @GetMapping(path =
    "parking-facilities/{facilityId}")
    public ParkingFacility getParkingFacility(
        @PathVariable Long facilityId
    ) {
        return parkingFacilityService.getParkingFacility(facilityId);
    }

    @PostMapping(path =
    "shops/{shopId}/parking-facilities/add")
    public ParkingFacility addParkingFacility(
        @PathVariable Long shopId,
        @RequestParam String address
    )  throws Exception {
        return parkingFacilityService.addParkingFacility(shopId, address);
    }
}
