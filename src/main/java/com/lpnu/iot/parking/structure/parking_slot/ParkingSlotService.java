package com.lpnu.iot.parking.structure.parking_slot;

import com.lpnu.iot.parking.resources.ParkingSlot;
import com.lpnu.iot.parking.resources.ParkingTicket;
import com.lpnu.iot.parking.structure.parking_facility.ParkingFacilityRepository;
import com.lpnu.iot.parking.structure.parking_ticket.ParkingTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class ParkingSlotService {

    @Autowired
    ParkingSlotRepository parkingSlotRepository;

    @Autowired
    ParkingTicketRepository parkingTicketRepository;



    public Map<Long, ParkingSlot> getParkingSlots(Long parkingFacilityId) {

        return parkingSlotRepository.findAll(parkingSlot ->
                parkingSlot.parkingFacilityId.equals(parkingFacilityId));
    }

    public ParkingSlot getParkingSlot(
            Long parkingSlotId
    ) {
        return parkingSlotRepository.findById(parkingSlotId);
    }

    public ParkingSlot addParkingSlot(
            Long parkingFacilityId,
            Boolean forDisabled
    ) {
        return parkingSlotRepository.save(new ParkingSlot(parkingFacilityId, ParkingSlot.FREE_SLOT_TICKET, forDisabled));
    }

    public ParkingSlot removeParkingSlot(Long parkingSlotId) {
        return parkingSlotRepository.remove(parkingSlotId);
    }

    public ParkingTicket takeParkingSlot(
            Long parkingFacilityId,
            String carNumber,
            Boolean forDisabled,
            Long clientId
    ) {

        Boolean isForDisabled = forDisabled != null ? forDisabled : false;
        ParkingSlot eligibleParkingSlot =
                parkingSlotRepository.findAny(parkingSlot ->
                parkingSlot.parkingFacilityId.equals(parkingFacilityId)
                && parkingSlot.activeTicketId.equals(ParkingSlot.FREE_SLOT_TICKET)
                && parkingSlot.isForDisabled.equals(isForDisabled));

        var newTicket = parkingTicketRepository.save(new ParkingTicket(
                clientId,
                parkingFacilityId,
                new Date(),
                null,
                carNumber,
                eligibleParkingSlot.id));

        eligibleParkingSlot.activeTicketId = newTicket.id;

        return newTicket;
    }

    public ParkingSlot freeParkingSlot(Long ticketId) {

        var ticketToDeactivate =  parkingTicketRepository.findById(ticketId);
        ticketToDeactivate.timeOfDeactivation = new Date();

        var slotToFree =  parkingSlotRepository.findById(ticketToDeactivate.parkingSlotId);
        slotToFree.activeTicketId = ParkingSlot.FREE_SLOT_TICKET;

        return slotToFree;
    }

}
