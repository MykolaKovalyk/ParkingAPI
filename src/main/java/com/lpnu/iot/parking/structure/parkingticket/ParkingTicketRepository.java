package com.lpnu.iot.parking.structure.parkingticket;

import com.lpnu.iot.parking.resources.ParkingTicket;
import com.lpnu.iot.parking.structure.CSVRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingTicketRepository extends CSVRepository<ParkingTicket> {
    @Override
    protected ParkingTicket createNewResource() {
        return new ParkingTicket();
    }

    public ParkingTicketRepository() {
        super("data/parking_tickets.csv");
    }
}
