package com.lpnu.iot.parking.structure.parking_slot;

import com.lpnu.iot.parking.structure.parking_ticket.ParkingTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSlotService {

    @Autowired
    ParkingSlotRepository parkingSlotRepository;

    @Autowired
    ParkingTicketRepository parkingTicketRepository;

}
