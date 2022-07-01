package com.lpnu.iot.parking.structure.repositories;

import com.lpnu.iot.parking.resources.ParkingTicket;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class ParkingTicketRepository extends CSVRepository<ParkingTicket> {

    public static final SimpleDateFormat FILE_NAME_DATE_FORMAT = new SimpleDateFormat("yyyy_MM_dd");
    private Date fileCreationDate;

    public ParkingTicketRepository() {
        super("data/parking_tickets");
    }

    @Override
    protected ParkingTicket createNewResource() {
        return new ParkingTicket();
    }
}
