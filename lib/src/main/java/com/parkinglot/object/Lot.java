package com.parkinglot.object;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parkinglot.fee.FeeModel;
import com.parkinglot.fee.FeeModelFactory;

public class Lot {
  List<Spot> allSpots;
  FeeModel feeModel;
  LotType lotType;


  public Lot(List<Spot> spots, LotType lotType) {
    this.allSpots = spots;
    this.lotType = lotType;
    this.feeModel = new FeeModelFactory().getFeeModel(this.lotType);
  }

  private int getFreeSpot(VehicleType vehicleType) {
    for (int i = 0; i < this.allSpots.size(); i++) {
      Spot currentSpot = this.allSpots.get(i);
      if (currentSpot.getVehicleType().equals(vehicleType) && currentSpot.isFree()) {
        currentSpot.setFree(false);
        this.allSpots.set(i, currentSpot);
        return currentSpot.getSpotId();
      }
    }
    System.out.println("No space available");
    return -1;
  }

  private void unFreeSpot(Ticket ticket) {
    for (int i = 0; i < this.allSpots.size(); i++) {
      Spot currentSpot = this.allSpots.get(i);
      if (currentSpot.getSpotId() == ticket.getSpotNumber()
          && currentSpot.getVehicleType().equals(ticket.getVechileType())) {
        currentSpot.setFree(true);
        this.allSpots.set(i, currentSpot);
      }
    }
  }

  public Ticket park(String vehicle) {
    Ticket ticket = null;
    VehicleType vehicleType = getVehicleType(vehicle);
    if(vehicleType!=null) {
      int spotNumber = getFreeSpot(vehicleType);     
      if (spotNumber > 0) {
        ticket = new Ticket(spotNumber, new Date(), vehicleType);
      }
    }
    return ticket;
  }

  public Receipt unPark(Ticket ticket, Date exitTime) {
    int fees = this.feeModel.calculateFees(ticket.getVechileType(), ticket, exitTime);
    unFreeSpot(ticket);
    Receipt receipt = new Receipt(ticket.getEntryDateTime(), exitTime, fees);
    return receipt;
  }

  public VehicleType getVehicleType(String vehicle) {
    if(vehicle.equalsIgnoreCase("motorcycle") || vehicle.equalsIgnoreCase("scooter")) {
      return VehicleType.TWO_WHEELER;
    }
    if(vehicle.equalsIgnoreCase("car") || vehicle.equalsIgnoreCase("suv")) {
      return VehicleType.FOUR_WHEELER;
    }
    if(vehicle.equalsIgnoreCase("bus") || vehicle.equalsIgnoreCase("truck")) {
      return VehicleType.HEAVY_DUTY;
    }
    System.out.println("Vechile Type not in list");
    return null;
  }
  
  public static class LotBuilder {
    Map<VehicleType, Integer> lotSizeMap;
    LotType lotType;

    public LotBuilder() {
      this.lotSizeMap = new HashMap<VehicleType, Integer>();
    }

    public LotBuilder setLotSizeByVechile(VehicleType vehicleType, int size) {
      this.lotSizeMap.put(vehicleType, size);
      return this;
    }

    public LotBuilder setLotType(LotType lotType) {
      this.lotType = lotType;
      return this;
    }

    public Lot build() {
      List<Spot> spots = new ArrayList<>();
      for (Map.Entry<VehicleType, Integer> entry : this.lotSizeMap.entrySet())  {
        VehicleType vehicleType = entry.getKey();
        spots.addAll(getDefaultSpots(vehicleType, this.lotSizeMap.get(vehicleType)));
      }

      return new Lot(spots, this.lotType);
    }

    private List<Spot> getDefaultSpots(VehicleType vehicleType, int size) {
      List<Spot> spotList = new ArrayList<Spot>();
      for (int i = 0; i < size; i++) {
        spotList.add(new Spot(vehicleType, i + 1));
      }
      return spotList;
    }
  }
  
  



}
