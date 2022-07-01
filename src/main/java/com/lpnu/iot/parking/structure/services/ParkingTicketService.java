package com.lpnu.iot.parking.structure.services;

import com.lpnu.iot.parking.resources.ParkingTicket;
import com.lpnu.iot.parking.structure.repositories.ParkingTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        var found = parkingTicketRepository.findById(ticketId);
        if (found == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ticket with id " + ticketId + " doesn't exist.");
        }

        return found;
    }
}
