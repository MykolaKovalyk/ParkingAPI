package com.lpnu.iot.parking.structure.parking_ticket;

import com.lpnu.iot.parking.resources.ParkingTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Service
public class ParkingTicketService {

    @Autowired
    ParkingTicketRepository parkingTicketRepository;



    public Map<Long, ParkingTicket> getTickets(Long facilityId) {
        return parkingTicketRepository.findAll(parkingTicket ->
                parkingTicket.parkingFacilityId.equals(facilityId));
    }

    public ParkingTicket getTicket(Long ticketId) {
        return parkingTicketRepository.findById(ticketId);
    }
}
