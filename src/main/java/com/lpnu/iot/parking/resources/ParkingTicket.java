package com.lpnu.iot.parking.resources;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingTicket extends Resource {

    Long ownerClientId;
    Date timeOfActivation;
    Date timeOfDeactivation;
    String carNumber;
    Long parkingSlotId;

    @Override
    public String[] toListOfStrings() {
        return new String[] {
                Long.toString(id),
                Long.toString(ownerClientId),
                dateFormat.format(timeOfActivation),
                dateFormat.format(timeOfDeactivation),
                carNumber,
                Long.toString(parkingSlotId)
        };
    }

    @Override
    public void fromListOfStrings(String[] csv) {
        id =  Long.parseLong(csv[0]);
        ownerClientId = Long.parseLong(csv[1]);

        try {
            timeOfActivation = dateFormat.parse(csv[2]);
            timeOfDeactivation = dateFormat.parse(csv[3]);
        } catch(ParseException e) {
            System.out.println("Parsing datetime failed!");
        }

        carNumber = csv[4];
        parkingSlotId =  Long.parseLong(csv[5]);
    }
}
