package com.lpnu.iot.parking.structure.parking_ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingTicketService {

    @Autowired
    ParkingTicketRepository parkingTicketRepository;
}
