package com.parkinglot.object;

import java.util.Date;

public class Ticket {
  private static int counter = 1;

  private String ticketNumber;
  private int spotNumber;
  private Date entryDateTime;
  private VehicleType vehicleType;

  public Ticket(int spotNumber, Date entryDateTime, VehicleType vehicleType) {
    this.entryDateTime = entryDateTime;
    this.spotNumber = spotNumber;
    this.ticketNumber = String.format("%03d", counter++);
    this.vehicleType = vehicleType;
  }

  public String getTicketNumber() {
    return this.ticketNumber;
  }

  public int getSpotNumber() {
    return this.spotNumber;
  }

  public Date getEntryDateTime() {
    return this.entryDateTime;
  }
  
  public VehicleType getVechileType() {
    return this.vehicleType;
  }
  
  public void printTicket() {
    System.out.println("Parking Ticket");
    System.out.println(" Ticker Number: " +this.getTicketNumber());
    System.out.println(" Spot Number: " +this.getSpotNumber());
    System.out.println(" Entry Date-Time: " + Util.getFormattedDate(this.getEntryDateTime()));
    System.out.println();
  }

}
