package com.lpnu.iot.parking.structure.parkingticket;

import com.lpnu.iot.parking.resources.ParkingTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ParkingTicketService {

    @Autowired
    private ParkingTicketRepository parkingTicketRepository;



    public Map<Long, ParkingTicket> getTickets(Long facilityId) {
        return parkingTicketRepository.findAll(parkingTicket ->
                parkingTicket.getParkingFacilityId().equals(facilityId));
    }

    public ParkingTicket getTicket(Long ticketId) {
        return parkingTicketRepository.findById(ticketId);
    }
}
