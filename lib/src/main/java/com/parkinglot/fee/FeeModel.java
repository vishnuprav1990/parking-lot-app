package com.parkinglot.fee;

import java.util.Date;

import com.parkinglot.object.Ticket;
import com.parkinglot.object.VehicleType;

public interface FeeModel {
    public int calculateFees(VehicleType vehicleType, Ticket ticket,Date exitTime);
}
