package com.lpnu.iot.parking.structure.parking_slot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ParkingSlotController {

    @Autowired
    ParkingSlotService parkingSlotService;
}
