package com.lpnu.iot.parking.structure.parking_slot;

import com.lpnu.iot.parking.resources.ParkingSlot;
import com.lpnu.iot.parking.structure.CSVRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingSlotRepository extends CSVRepository<ParkingSlot> {
    @Override
    protected ParkingSlot createNewResource() {
        return new ParkingSlot();
    }
}
