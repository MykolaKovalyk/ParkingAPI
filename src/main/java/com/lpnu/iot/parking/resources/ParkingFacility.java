package com.lpnu.iot.parking.resources;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ParkingFacility extends Resource {

    public Long belongingShopId;

    @Override
    public String[] toListOfStrings() {
        return new String[] {
                Long.toString(id),
                Long.toString(belongingShopId)
        };
    }

    @Override
    public void fromListOfStrings(String[] csv) {
        id = Long.parseLong(csv[0]);
        belongingShopId = Long.parseLong(csv[1]);
    }
}
