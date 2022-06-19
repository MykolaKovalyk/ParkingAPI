package com.lpnu.iot.parking.structure.parkingslot;

import com.lpnu.iot.parking.resources.ParkingSlot;
import com.lpnu.iot.parking.resources.ParkingTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;



import java.util.Map;

@RestController
public class ParkingSlotController {

    @Autowired
    private ParkingSlotService parkingSlotService;

    @GetMapping(path =
    "parking-facilities/{parkingFacilityId}/parking-slots")
    public Map<Long, ParkingSlot> getParkingSlots(
            @PathVariable Long parkingFacilityId
    ) {
        return parkingSlotService.getParkingSlots(parkingFacilityId);
    }

    @GetMapping(path =
    "parking-slots/{parkingSlotId}")
    public ParkingSlot getParkingSlot(
            @PathVariable Long parkingSlotId
    ) {
        return parkingSlotService.getParkingSlot(parkingSlotId);
    }

    @PostMapping(path =
    "parking-facilities/{parkingFacilityId}/parking-slots/add")
    public ParkingSlot addParkingSlot(
            @PathVariable Long parkingFacilityId,
            @RequestParam(required = false) Boolean forDisabled
    ) {
        return parkingSlotService.addParkingSlot(
                parkingFacilityId,
                forDisabled
        );
    }

    @DeleteMapping(path =
    "parking-slots/{parkingSlotId}/remove")
    public ParkingSlot removeParkingSlot(
            @PathVariable Long parkingSlotId
    ) {
        return parkingSlotService.removeParkingSlot(parkingSlotId);
    }

    @PutMapping(path =
    "parking-facilities/{parkingFacilityId}/take-slot/{carNumber}")
    public ParkingTicket takeParkingSlot(
            @PathVariable Long parkingFacilityId,
            @PathVariable String carNumber,
            @RequestParam(required = false) Boolean forDisabled,
            @RequestParam(required = false) Long clientId
    ) {
        return parkingSlotService.takeParkingSlot(
                parkingFacilityId,
                carNumber,
                forDisabled,
                clientId
        );
    }

    @PutMapping(path =
            "parking-tickets/{ticketId}/free")
    public ParkingSlot freeParkingSlot(
            @PathVariable Long ticketId
    ) {
        return parkingSlotService.freeParkingSlot(ticketId);
    }
}
