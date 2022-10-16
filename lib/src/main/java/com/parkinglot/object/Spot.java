package com.parkinglot.object;

public class Spot {
  private int spotId;
  private VehicleType vehicleType;
  private boolean isFree;
  
  public Spot(VehicleType vehicleType, int spotId) {
    this.vehicleType = vehicleType;
    this.isFree = true;
    this.spotId = spotId;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public boolean isFree() {
    return isFree;
  }

  public void setFree(boolean isFree) {
    this.isFree = isFree;
  }
  
  public int getSpotId() {
    return this.spotId;
  }


}
