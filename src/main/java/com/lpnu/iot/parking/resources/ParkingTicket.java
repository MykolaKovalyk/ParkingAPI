package com.lpnu.iot.parking.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingTicket extends Resource {

    private Long ownerClientId;
    private Long parkingFacilityId;
    private Date timeOfActivation;
    private Date timeOfDeactivation;
    private String carNumber;
    private Long parkingSlotId;

    @Override
    public String[] getFieldValues() {
        return new String[]{
                Long.toString(getId()),
                Long.toString(ownerClientId),
                Long.toString(parkingFacilityId),
                DATE_FORMAT.format(timeOfActivation),
                timeOfDeactivation == null ? null : DATE_FORMAT.format(timeOfDeactivation),
                carNumber,
                Long.toString(parkingSlotId)
        };
    }

    @Override
    public String[] getFieldNames() {
        return new String[]{
                "id",
                "ownerClientId",
                "parkingFacilityId",
                "timeOfActivation",
                "timeOfDeactivation",
                "carNumber",
                "parkingSlotId"};
    }

    @Override
    public void setFieldValues(String[] csv) {
        setId(Long.parseLong(csv[0]));
        ownerClientId = Long.parseLong(csv[1]);
        parkingFacilityId = Long.parseLong(csv[2]);

        try {
            timeOfActivation = csv[3].length() > 0 ? DATE_FORMAT.parse(csv[3]) : null;
            timeOfDeactivation = csv[4].length() > 0 ? DATE_FORMAT.parse(csv[4]) : null;
        } catch (ParseException e) {
            System.out.println("Parsing datetime failed!");
        }

        carNumber = csv[5];
        parkingSlotId = Long.parseLong(csv[6]);
    }
}
