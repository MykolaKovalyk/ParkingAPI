package com.lpnu.iot.parking.resources;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class ParkingSlot extends Resource {

    public static final Long FREE_SLOT_TICKET = 0L;

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
