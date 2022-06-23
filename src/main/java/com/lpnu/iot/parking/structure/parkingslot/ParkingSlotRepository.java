package com.lpnu.iot.parking.structure.parkingslot;

import com.lpnu.iot.parking.resources.ParkingSlot;
import com.lpnu.iot.parking.structure.CSVRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingSlotRepository extends CSVRepository<ParkingSlot> {
    public ParkingSlotRepository() {
        super("data/parking_slots");
    }

    @Override
    protected ParkingSlot createNewResource() {
        return new ParkingSlot();
    }
}
