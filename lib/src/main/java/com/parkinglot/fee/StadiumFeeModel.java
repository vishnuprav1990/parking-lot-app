package com.parkinglot.fee;

import java.util.Date;

import com.parkinglot.object.Ticket;
import com.parkinglot.object.Util;
import com.parkinglot.object.VehicleType;

public class StadiumFeeModel implements FeeModel {

  @Override
  public int calculateFees(VehicleType vehicleType, Ticket ticket, Date exitTime) {
    int parkedHrs = Util.dateDifference(ticket.getEntryDateTime(), exitTime,true);
    int fees = 0;

    switch (vehicleType) {
      case TWO_WHEELER:
        fees = getFee(parkedHrs);
        break;
      case FOUR_WHEELER:
        fees = getFee(parkedHrs) * 2;
        break;
      default:
        fees = 0;
        break;
    }
    return fees;
  }

  private int getFee(int parkedHrs) {
    int fees = 0;
    if (parkedHrs > 0) {
      fees += 30;
      parkedHrs -= 4;
    }
    if (parkedHrs > 0) {
      fees += 60;
      parkedHrs -= 8;
    }
    if (parkedHrs > 0) {
      fees = fees + (parkedHrs * 100);
    }
    return fees;
  }



}
