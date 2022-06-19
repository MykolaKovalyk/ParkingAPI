package com.lpnu.iot.parking.structure.parkingticket;

import com.lpnu.iot.parking.resources.ParkingTicket;
import com.lpnu.iot.parking.structure.CSVRepository;
import org.apache.tomcat.jni.File;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Repository
public class ParkingTicketRepository extends CSVRepository<ParkingTicket> {

    public static final SimpleDateFormat FILE_NAME_DATE_FORMAT = new SimpleDateFormat("yyyy_MM_dd");

    private Date fileCreationDate;


    @Override
    protected ParkingTicket createNewResource() {
        return new ParkingTicket();
    }

    public ParkingTicketRepository() {
        super("data/parking_tickets_"
                + FILE_NAME_DATE_FORMAT.format(new Date())
                + ".csv");
    }

    @Override
    public void saveToFileIfNecessary() {
        setFilePath("data/parking_tickets_"
                + FILE_NAME_DATE_FORMAT.format(new Date())
                + ".csv");
        super.saveToFileIfNecessary();
    }
}
