package com.lpnu.iot.parking.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingFacility extends Resource {

    private Long belongingShopId;
    private String address;
    private Integer countOfParkingSlots;

    @Override
    public String[] toArrayOfStrings() {
        return new String[] {
                Long.toString(getId()),
                Long.toString(belongingShopId),
                address,
                Integer.toString(countOfParkingSlots)
        };
    }

    @Override
    public String[] fieldNamesToStringArray() {
        return new String[] {
                "id",
                "belongingShopId",
                "address",
                "countOfParkingSlots"
        };
    }

    @Override
    public void fromArrayOfStrings(String[] csv) {
        setId(Long.parseLong(csv[0]));
        belongingShopId = Long.parseLong(csv[1]);
        address = csv[2];
        countOfParkingSlots = Integer.parseInt(csv[3]);
    }
}
