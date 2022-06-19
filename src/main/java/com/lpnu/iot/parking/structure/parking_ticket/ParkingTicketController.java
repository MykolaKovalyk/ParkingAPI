package com.lpnu.iot.parking.structure.parking_ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ParkingTicketController {

    @Autowired
    ParkingTicketService parkingTicketService;
}
