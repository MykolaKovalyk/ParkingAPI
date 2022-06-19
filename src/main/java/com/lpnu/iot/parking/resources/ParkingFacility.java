package com.lpnu.iot.parking.resources;

public class ParkingFacility extends Resource {

    public Long belongingShopId;
    public Integer numberOfSlots;

    @Override
    public String[] toListOfStrings() {
        return new String[] {
                Long.toString(id),
                Long.toString(belongingShopId),
                Integer.toString(numberOfSlots)
        };
    }

    @Override
    public void fromListOfStrings(String[] csv) {
        id = Long.parseLong(csv[0]);
        belongingShopId = Long.parseLong(csv[1]);
        numberOfSlots = Integer.parseInt(csv[2]);
    }
}
