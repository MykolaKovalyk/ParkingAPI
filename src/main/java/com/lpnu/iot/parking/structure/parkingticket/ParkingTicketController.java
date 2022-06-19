package com.lpnu.iot.parking.structure.parkingticket;

import com.lpnu.iot.parking.resources.ParkingTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ParkingTicketController {

    @Autowired
    private ParkingTicketService parkingTicketService;


    @GetMapping(path =
    "parking-facilities/{facilityId}/parking-tickets")
    public Map<Long, ParkingTicket> getTickets(
            @PathVariable Long facilityId
    ) {
        return parkingTicketService.getTickets(facilityId);
    }


    @GetMapping(path =
    "parking-tickets/{ticketId}")
    public ParkingTicket getTicket(
            @PathVariable Long ticketId
    ) {
        return parkingTicketService.getTicket(ticketId);
    }

}
