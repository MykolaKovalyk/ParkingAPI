package com.lpnu.iot.parking.structure.parkingslot;

import com.lpnu.iot.parking.resources.ParkingSlot;
import com.lpnu.iot.parking.resources.ParkingTicket;
import com.lpnu.iot.parking.structure.parkingticket.ParkingTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private ParkingTicketRepository parkingTicketRepository;



    public Map<Long, ParkingSlot> getParkingSlots(Long parkingFacilityId) {

        return parkingSlotRepository.findAll(parkingSlot ->
                parkingSlot.getParkingFacilityId().equals(parkingFacilityId));
    }

    public ParkingSlot getParkingSlot(
            Long parkingSlotId
    ) {
        return parkingSlotRepository.findById(parkingSlotId);
    }

    public ParkingSlot addParkingSlot(
            Long parkingFacilityId,
            Boolean forDisabled
    )  throws Exception {

        var added = parkingSlotRepository.save(
                new ParkingSlot(
                        parkingFacilityId,
                        ParkingSlot.FREE_SLOT_TICKET,
                        forDisabled));


        parkingSlotRepository.writeDataToFile();

        return added;
    }

    public ParkingSlot removeParkingSlot(Long parkingSlotId)  throws Exception {
        var removed = parkingSlotRepository.remove(parkingSlotId);

        parkingSlotRepository.writeDataToFile();
        parkingTicketRepository.writeDataToFile();

        return removed;
    }

    public ParkingTicket takeParkingSlot(
            Long parkingFacilityId,
            String carNumber,
            Boolean forDisabled,
            Long clientId
    )  throws Exception {

        Boolean isForDisabled = forDisabled != null ? forDisabled : false;
        ParkingSlot eligibleParkingSlot =
                parkingSlotRepository.findAny(parkingSlot ->
                parkingSlot.getParkingFacilityId().equals(parkingFacilityId)
                && parkingSlot.getActiveTicketId().equals(ParkingSlot.FREE_SLOT_TICKET)
                && parkingSlot.getIsForDisabled().equals(isForDisabled));

        var newTicket = parkingTicketRepository.save(new ParkingTicket(
                clientId,
                parkingFacilityId,
                new Date(),
                null,
                carNumber,
                eligibleParkingSlot.getId()));

        eligibleParkingSlot.setActiveTicketId(newTicket.getId());

        parkingSlotRepository.writeDataToFile();
        parkingTicketRepository.writeDataToFile();

        return newTicket;
    }

    public ParkingSlot freeParkingSlot(Long ticketId)  throws Exception {

        var ticketToDeactivate =  parkingTicketRepository.findById(ticketId);
        ticketToDeactivate.setTimeOfDeactivation(new Date());

        var slotToFree =  parkingSlotRepository.findById(ticketToDeactivate.getParkingSlotId());
        slotToFree.setActiveTicketId(ParkingSlot.FREE_SLOT_TICKET);

        parkingSlotRepository.writeDataToFile();
        parkingTicketRepository.writeDataToFile();

        return slotToFree;
    }

}
