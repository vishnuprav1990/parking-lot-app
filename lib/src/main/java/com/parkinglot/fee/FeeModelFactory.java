package com.parkinglot.fee;

import com.parkinglot.object.LotType;

public class FeeModelFactory {

  public FeeModel getFeeModel(LotType feeModel) {
    if (feeModel == null) {
      return null;
    }
    if (feeModel.equals(LotType.MALL)) {
      return new MallFeeModel();

    } else if (feeModel.equals(LotType.STADIUM)) {
      return new StadiumFeeModel();

    } else if (feeModel.equals(LotType.AIRPORT)) {
      return new AirportFeeModel();
    }
    return null;
  }

}
