package com.lpnu.iot.parking.resources;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class ParkingTicket extends Resource {

    public Long ownerClientId;
    public Long parkingFacilityId;
    public Date timeOfActivation;
    public Date timeOfDeactivation;
    public String carNumber;
    public Long parkingSlotId;

    @Override
    public String[] toListOfStrings() {
        return new String[] {
                Long.toString(id),
                Long.toString(ownerClientId),
                Long.toString(parkingFacilityId),
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
        parkingFacilityId =  Long.parseLong(csv[2]);

        try {
            timeOfActivation = dateFormat.parse(csv[3]);
            timeOfDeactivation = dateFormat.parse(csv[4]);
        } catch(ParseException e) {
            System.out.println("Parsing datetime failed!");
        }

        carNumber = csv[5];
        parkingSlotId =  Long.parseLong(csv[6]);
    }
}
