package com.parkinglot.fee;

import java.util.Date;

import com.parkinglot.object.Ticket;
import com.parkinglot.object.Util;
import com.parkinglot.object.VehicleType;

public class AirportFeeModel implements FeeModel {

  @Override
  public int calculateFees(VehicleType vehicleType, Ticket ticket, Date exitTime) {
    boolean isRoundedHrs = VehicleType.TWO_WHEELER.equals(vehicleType) ? false : true;
    int parkedHrs = Util.dateDifference(ticket.getEntryDateTime(), exitTime,isRoundedHrs);
    int fees = 0;

    switch (vehicleType) {
      case TWO_WHEELER:
        fees = getTwoWheelerFee(parkedHrs);
        break;
      case FOUR_WHEELER:
        fees = getFourWheelerFee(parkedHrs);
        break;
      default:
        fees = 0;
        break;
    }
    return fees;
  }

  private int getTwoWheelerFee(int parkedHrs) {
    int fees = 0;
    if (parkedHrs > 24) {
      int days = parkedHrs / 24 ;
      if(parkedHrs % 24 >0) {
        days+=1;
      }
    
      return days * 80;
    }
    if (parkedHrs > 1) {
      fees = 40;
      parkedHrs -= 8;
    }
    if (parkedHrs > 0) {
      fees = 60;
    }
    return fees;
  }

  private int getFourWheelerFee(int parkedHrs) {
    int fees = 0;
    if (parkedHrs > 24) {
      fees = (parkedHrs / 24) * 100;
      if (fees % 24 != 0) {
        fees += 100;
      }
      return fees;
    }

    if (parkedHrs > 0) {
      fees = 60;
      parkedHrs -= 12;
    }
    if (parkedHrs > 0) {
      fees = 80;
    }
    return fees;
  }

}
