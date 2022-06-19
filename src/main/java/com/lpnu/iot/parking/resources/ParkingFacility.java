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

    @Override
    public String[] toArrayOfStrings() {
        return new String[] {
                Long.toString(getId()),
                Long.toString(belongingShopId)
        };
    }

    @Override
    public void fromArrayOfStrings(String[] csv) {
        setId(Long.parseLong(csv[0]));
        belongingShopId = Long.parseLong(csv[1]);
    }
}
