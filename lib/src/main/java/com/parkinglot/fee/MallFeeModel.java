package com.parkinglot.fee;

import java.util.Date;

import com.parkinglot.object.Ticket;
import com.parkinglot.object.Util;
import com.parkinglot.object.VehicleType;

public class MallFeeModel implements FeeModel{

  @Override
  public int calculateFees(VehicleType vehicleType,Ticket ticket,Date exitTime) {
    int parkedHrs = Util.dateDifference(ticket.getEntryDateTime(), exitTime, true);
    int fees;
     switch (vehicleType) {
      case TWO_WHEELER:
        fees = parkedHrs * 10;
        break;
      case FOUR_WHEELER:
        fees = parkedHrs * 20;
        break;
      case HEAVY_DUTY:
        fees = parkedHrs * 50;
        break;  
      default:
        fees = 0;
        break;
    }
    return fees;
  }

}
