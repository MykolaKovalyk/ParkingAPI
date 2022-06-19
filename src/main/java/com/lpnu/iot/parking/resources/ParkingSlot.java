package com.lpnu.iot.parking.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingSlot extends Resource {

    public static final Long FREE_SLOT_TICKET = 0L;

    private Long parkingFacilityId;
    private Long activeTicketId;
    private Boolean isForDisabled;

    @Override
    public String[] toArrayOfStrings() {
        return new String[] {
                Long.toString(getId()),
                Long.toString(parkingFacilityId),
                Long.toString(activeTicketId),
                Boolean.toString(isForDisabled)
        };
    }

    @Override
    public String[] fieldNamesToStringArray() {
        return new String[] {
                "id",
                "parkingFacilityId",
                "activeTicketId",
                "isForDisabled"
        };
    }

    @Override
    public void fromArrayOfStrings(String[] csv) {
        setId(Long.parseLong(csv[0]));
        parkingFacilityId =  Long.parseLong(csv[1]);
        activeTicketId = Long.parseLong(csv[2]);
        isForDisabled = Boolean.parseBoolean(csv[3]);
    }
}
