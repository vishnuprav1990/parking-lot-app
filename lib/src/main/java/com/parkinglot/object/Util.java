package com.parkinglot.object;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
  public static int dateDifference(Date startDate, Date endDate, boolean isRoundedHrs) {
    Timestamp startTimeStamp = new Timestamp(startDate.getTime());

    Timestamp endTimeStamp = new Timestamp(endDate.getTime());

    long milliseconds = endTimeStamp.getTime() - startTimeStamp.getTime();
    int seconds = (int) milliseconds / 1000;

    int hours = seconds / 3600;
    int minutes = (seconds % 3600) / 60;
    return (isRoundedHrs || hours >0) && minutes > 0  ? hours + 1 : hours;
  }

  public static Date addHoursToJavaUtilDate(Date date, int minutes) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MINUTE, minutes);
    return calendar.getTime();
  }

  public static String getFormattedDate(Date date) {
    return new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss").format(date).toString();
  }


}
