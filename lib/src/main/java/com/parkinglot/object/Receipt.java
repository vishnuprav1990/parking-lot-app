package com.parkinglot.object;

import java.util.Date;

public class Receipt {
  private static int counter = 1;
  private String receiptNumber;
  private Date entryTime;
  private Date exitTime;
  private int fees;

  public Receipt(Date entryTime, Date exitTime, int fees) {
    this.receiptNumber = "R-" + String.format("%03d", counter++);
    this.entryTime = entryTime;
    this.exitTime = exitTime;
    this.fees = fees;
  }

  public String getReceiptNumber() {
    return receiptNumber;
  }

  public Date getEntryTime() {
    return entryTime;
  }

  public Date getExitTime() {
    return exitTime;
  }

  public int getFees() {
    return fees;
  }

  public void printReceipt() {
    System.out.println("Parking Receipt:");
    System.out.println(" Receipt Number: " + this.getReceiptNumber());
    System.out.println(" Entry Date-time: " + Util.getFormattedDate(this.getEntryTime()));
    System.out.println(" Exit Date-time: " + Util.getFormattedDate(this.getExitTime()));
    System.out.println(" Fees: " + this.getFees());
    System.out.println();

  }

}
