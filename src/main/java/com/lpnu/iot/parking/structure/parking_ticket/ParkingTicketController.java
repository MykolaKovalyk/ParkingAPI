package com.lpnu.iot.parking.structure.parking_ticket;

import com.lpnu.iot.parking.resources.ParkingTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ParkingTicketController {

    @Autowired
    ParkingTicketService parkingTicketService;


    @GetMapping(path=
    "parking_facilities/{facilityId}/parking_tickets")
    public Map<Long, ParkingTicket> getTickets(
            @PathVariable Long facilityId
    ) {
        return null;
    }


    @GetMapping(path=
    "parking_tickets/{ticketId}")
    public ParkingTicket getTicket(
            @PathVariable Long ticketId
    ) {
        return null;
    }

}
