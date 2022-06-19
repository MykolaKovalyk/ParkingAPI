package com.lpnu.iot.parking.structure.parking_slot;

import com.lpnu.iot.parking.resources.Client;
import com.lpnu.iot.parking.resources.ParkingSlot;
import com.lpnu.iot.parking.resources.ParkingTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class ParkingSlotController {

    @Autowired
    ParkingSlotService parkingSlotService;

    @GetMapping(path=
    "parking_facilities/{parkingFacilityId}/parking_slots")
    public Map<Long, ParkingSlot> getParkingSlots(
            @PathVariable Long parkingFacilityId
    ) {
        return null;
    }

    @GetMapping(path=
    "parking_facilities/{parkingFacilityId}/parking_slots/{parkingSlotId}")
    public ParkingSlot getParkingSlot(
            @PathVariable Long parkingFacilityId,
            @PathVariable Long parkingSlotId
    ) {
        return null;
    }

    @PostMapping(path=
    "parking_facilities/{parkingFacilityId}/parking_slots/add")
    public ParkingSlot addParkingSlot(
            @PathVariable Long parkingFacilityId,
            @RequestParam Boolean forDisabled
    ) {
        return null;
    }

    @DeleteMapping(path =
    "parking_slots/{parkingSlotId}/remove")
    public Boolean removeParkingSlot(
            @PathVariable Long parkingSlotId
    ) {
        return null;
    }

    @PutMapping(path=
    "parking_facilities/{parkingFacilityId}/take_slot/{carNumber}")
    public ParkingTicket takeParkingSlot(
            @PathVariable Long parkingFacilityId,
            @PathVariable String carNumber,
            @RequestParam Boolean forDisabled,
            @RequestParam Long clientId
    ) {
        return null;
    }

    @PutMapping(path=
            "parking_tickets/{ticketId}/free")
    public Boolean freeParkingSlot(
            @PathVariable Long ticketId
    ) {
        return null;
    }
}
