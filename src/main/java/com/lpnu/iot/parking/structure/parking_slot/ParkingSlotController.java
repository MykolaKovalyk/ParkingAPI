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
        return parkingSlotService.getParkingSlots(parkingFacilityId);
    }

    @GetMapping(path=
    "parking_slots/{parkingSlotId}")
    public ParkingSlot getParkingSlot(
            @PathVariable Long parkingSlotId
    ) {
        return parkingSlotService.getParkingSlot(parkingSlotId);
    }

    @PostMapping(path=
    "parking_facilities/{parkingFacilityId}/parking_slots/add")
    public ParkingSlot addParkingSlot(
            @PathVariable Long parkingFacilityId,
            @RequestParam Boolean forDisabled
    ) {
        return parkingSlotService.addParkingSlot(
                parkingFacilityId,
                forDisabled
        );
    }

    @DeleteMapping(path =
    "parking_slots/{parkingSlotId}/remove")
    public ParkingSlot removeParkingSlot(
            @PathVariable Long parkingSlotId
    ) {
        return parkingSlotService.removeParkingSlot(parkingSlotId);
    }

    @PutMapping(path=
    "parking_facilities/{parkingFacilityId}/take_slot/{carNumber}")
    public ParkingTicket takeParkingSlot(
            @PathVariable Long parkingFacilityId,
            @PathVariable String carNumber,
            @RequestParam Boolean forDisabled,
            @RequestParam Long clientId
    ) {
        return parkingSlotService.takeParkingSlot(
                parkingFacilityId,
                carNumber,
                forDisabled,
                clientId
        );
    }

    @PutMapping(path=
            "parking_tickets/{ticketId}/free")
    public ParkingSlot freeParkingSlot(
            @PathVariable Long ticketId
    ) {
        return parkingSlotService.freeParkingSlot(ticketId);
    }
}
