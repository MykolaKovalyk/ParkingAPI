package com.lpnu.iot.parking.resources;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;

public class ParkingSlot extends Resource {

    public Long parkingFacilityId;
    public Long activeTicketId;
    public Boolean isForDisabled;

    @Override
    public String[] toListOfStrings() {
        return new String[] {
                Long.toString(id),
                Long.toString(parkingFacilityId),
                Long.toString(activeTicketId),
                Boolean.toString(isForDisabled)
        };
    }

    @Override
    public void fromListOfStrings(String[] csv) {
        id =  Long.parseLong(csv[0]);
        parkingFacilityId =  Long.parseLong(csv[1]);
        activeTicketId = Long.parseLong(csv[2]);
        isForDisabled = Boolean.parseBoolean(csv[3]);
    }
}
