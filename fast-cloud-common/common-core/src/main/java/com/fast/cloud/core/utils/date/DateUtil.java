package com.fast.cloud.core.utils.date;


import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
  public static final String FORMAT_DATE_YMDHMS = "yyyy-MM-dd HH:mm:ss";
  public static final String FORMAT_DATE_YMD = "yyyy-MM-dd";


  public static String fromISODate(String time){
    time=time.split("\\+")[0]+"Z";
    if(!time.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z")){
      return null;
    }
    time=time.replaceFirst("T", " ").replaceFirst(".\\d{3}Z", "");
//    Date date=format(time,null);
//    Calendar ca=Calendar.getInstance();
//    ca.setTime(date);
//    ca.add(Calendar.HOUR_OF_DAY, 8);
//    format(ca.getTime().getTime()+"")+""
    return time;
  }


  public static String format(Date date)
  {
    return format(date, "yyyy-MM-dd HH:mm:ss");
  }
  
  public static Date convertStringToDate(String date, String pattern)
  {
    if (date == "") {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    try
    {
      return sdf.parse(date);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return new Date();
  }
  
  public static String format(Date date, String pattern)
  {
    if (date == null) {
      return "null";
    }
    if ((pattern == null) || (pattern.equals("")) || (pattern.equals("null"))) {
      pattern = "yyyy-MM-dd HH:mm:ss";
    }
    return new SimpleDateFormat(pattern).format(date);
  }
  
  public static Date format(String date)
  {
    return format(date, null);
  }
  
  public static Date format(String date, String pattern)
  {
    if ((pattern == null) || (pattern.equals("")) || (pattern.equals("null"))) {
      pattern = "yyyy-MM-dd HH:mm:ss";
    }
    if ((date == null) || (date.equals("")) || (date.equals("null"))) {
      return new Date();
    }
    Date d = null;
    try
    {
      d = new SimpleDateFormat(pattern).parse(date);
    }
    catch (ParseException localParseException) {}
    return d;
  }
  
  public static String getDateYmdHms()
  {
    Date date = new Date();
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
    String now = formatter1.format(date);
    return now;
  }
  
  public static Date getNextDay(Date date)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(5, 1);
    date = c.getTime();
    return date;
  }
  
  public static Date getNextHour(Date date)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(11, 1);
    date = c.getTime();
    return date;
  }
  
  public static Date getPreviousMonth(Date date)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(2, -1);
    date = c.getTime();
    return date;
  }
  
  public static Date getPreviousWeek(Date date)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(5, -7);
    date = c.getTime();
    return date;
  }
  
  public static Date getLastDayOfMonth()
  {
    String dateFormat = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Calendar lastDate = Calendar.getInstance();
    lastDate.set(5, 1);
    lastDate.add(2, 1);
    lastDate.add(5, -1);
    
    return strToDate(sdf.format(lastDate.getTime()), dateFormat);
  }
  
  public static String getLastDayOfMonthStr()
  {
    String dateFormat = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Calendar lastDate = Calendar.getInstance();
    lastDate.set(5, 1);
    lastDate.add(2, 1);
    lastDate.add(5, -1);
    
    return sdf.format(lastDate.getTime());
  }
  
  public static String getFirstDayOfPreviousMonth()
  {
    String dateFormat = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Calendar lastDate = Calendar.getInstance();
    lastDate.set(5, 1);
    lastDate.add(2, -1);
    
    lastDate.set(11, 0);
    lastDate.set(12, 0);
    lastDate.set(13, 0);
    lastDate.set(14, 0);
    

    return sdf.format(lastDate.getTime());
  }
  
  public static String getLastDayOfBeforeMonth()
  {
    String dateFormat = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Calendar lastDate = Calendar.getInstance();
    lastDate.set(5, 1);
    lastDate.add(2, 0);
    lastDate.add(5, -1);
    
    lastDate.set(11, 23);
    lastDate.set(12, 59);
    lastDate.set(13, 59);
    lastDate.set(14, 999);
    
    return sdf.format(lastDate.getTime());
  }
  
  public static String getLastDayOfBeforeMonthYMD()
  {
    String dateFormat = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Calendar lastDate = Calendar.getInstance();
    lastDate.set(5, 1);
    lastDate.add(2, 0);
    lastDate.add(5, -1);
    
    return sdf.format(lastDate.getTime());
  }
  
  public static String getNowDate()
  {
    String dateFormat = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Calendar lastDate = Calendar.getInstance();
    
    return sdf.format(lastDate.getTime());
  }
  
  public static Date getFirstDayOfMonth()
  {
    String dateFormat = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Calendar lastDate = Calendar.getInstance();
    lastDate.set(5, 1);
    return strToDate(sdf.format(lastDate.getTime()), dateFormat);
  }
  
  public static String getFirstDayOfTheMonth(int i)
  {
    String dateFormat = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Calendar lastDate = Calendar.getInstance();
    lastDate.set(5, 1 + i);
    return sdf.format(lastDate.getTime());
  }
  
  public static String getFirstDayOfTheBefordeMonth(int i)
  {
    String dateFormat = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Calendar lastDate = Calendar.getInstance();
    lastDate.add(2, -1);
    lastDate.set(5, 1 + i);
    return sdf.format(lastDate.getTime());
  }
  
  public static Date getFirstDayOfWeek()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(7, 2);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    return strToDate(df.format(cal.getTime()), "yyyy-MM-dd HH:mm:ss");
  }
  
  public static Date getLastDayOfWeek()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(7, 1);
    cal.add(3, 1);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    return strToDate(df.format(cal.getTime()), "yyyy-MM-dd HH:mm:ss");
  }
  
  public static Date getFirstDayOfMonthHMS()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(5, cal.getActualMinimum(5));
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    return strToDate(df.format(cal.getTime()), "yyyy-MM-dd HH:mm:ss");
  }
  
  public static Date getLastDayOfMonthHMS()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(5, cal.getActualMaximum(5));
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    return strToDate(df.format(cal.getTime()), "yyyy-MM-dd HH:mm:ss");
  }
  
  public static Date getNowTime(String dateformat)
  {
    Date now = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
    return strToDate(dateFormat.format(now), dateformat);
  }
  
  public static String getStartTime(String date)
  {
    String dateFromFormat = "yyyy-MM-dd";
    String dateToFormat = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat dateFormatFrom = new SimpleDateFormat(dateFromFormat);
    SimpleDateFormat dateFormatTo = new SimpleDateFormat(dateToFormat);
    Calendar todayStart = Calendar.getInstance();
    try
    {
      Date parsedDate = dateFormatFrom.parse(date);
      
      todayStart.setTime(parsedDate);
      todayStart.set(11, 0);
      todayStart.set(12, 0);
      todayStart.set(13, 0);
      todayStart.set(14, 0);
    }
    catch (ParseException ex)
    {
      ex.printStackTrace();
    }
    return dateFormatTo.format(todayStart.getTime());
  }
  
  public static String getDayStartTime(String date, String dateFromFormat, String dateToFormat)
  {
    SimpleDateFormat dateFormatFrom = new SimpleDateFormat(dateFromFormat);
    SimpleDateFormat dateFormatTo = new SimpleDateFormat(dateToFormat);
    Calendar todayStart = Calendar.getInstance();
    try
    {
      Date parsedDate = dateFormatFrom.parse(date);
      
      todayStart.setTime(parsedDate);
      todayStart.set(11, 0);
      todayStart.set(12, 0);
      todayStart.set(13, 0);
      todayStart.set(14, 0);
    }
    catch (ParseException ex)
    {
      ex.printStackTrace();
    }
    return dateFormatTo.format(todayStart.getTime());
  }
  
  public static String getEndTime(String date)
  {
    String dateFromFormat = "yyyy-MM-dd";
    String dateToFormat = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat dateFormatFrom = new SimpleDateFormat(dateFromFormat);
    SimpleDateFormat dateFormatTo = new SimpleDateFormat(dateToFormat);
    Calendar todayEnd = Calendar.getInstance();
    try
    {
      Date parsedDate = dateFormatFrom.parse(date);
      todayEnd.setTime(parsedDate);
      todayEnd.set(11, 23);
      todayEnd.set(12, 59);
      todayEnd.set(13, 59);
      todayEnd.set(14, 999);
    }
    catch (ParseException ex)
    {
      ex.printStackTrace();
    }
    return dateFormatTo.format(todayEnd.getTime());
  }
  
  public static String getDayEndTime(String date, String dateFromFormat, String dateToFormat)
  {
    SimpleDateFormat dateFormatFrom = new SimpleDateFormat(dateFromFormat);
    SimpleDateFormat dateFormatTo = new SimpleDateFormat(dateToFormat);
    Calendar todayEnd = Calendar.getInstance();
    try
    {
      Date parsedDate = dateFormatFrom.parse(date);
      todayEnd.setTime(parsedDate);
      todayEnd.set(11, 23);
      todayEnd.set(12, 59);
      todayEnd.set(13, 59);
      todayEnd.set(14, 999);
    }
    catch (ParseException ex)
    {
      ex.printStackTrace();
    }
    return dateFormatTo.format(todayEnd.getTime());
  }
  
  public static Date getStartDate(Date date)
  {
    Calendar todayStart = Calendar.getInstance();
    todayStart.setTime(date);
    todayStart.set(11, 0);
    todayStart.set(12, 0);
    todayStart.set(13, 0);
    todayStart.set(14, 0);
    return todayStart.getTime();
  }
  
  public static Date getEndDate(Date date)
  {
    Calendar todayEnd = Calendar.getInstance();
    todayEnd.setTime(date);
    todayEnd.set(11, 23);
    todayEnd.set(12, 59);
    todayEnd.set(13, 59);
    todayEnd.set(14, 999);
    return todayEnd.getTime();
  }
  
  public static Date getYesterday()
  {
    Calendar cal = Calendar.getInstance();
    cal.add(5, -1);
    return cal.getTime();
  }
  
  public static String getYesterdayStr()
  {
    Calendar cal = Calendar.getInstance();
    cal.add(5, -1);
    String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    return yesterday;
  }
  
  public static Date computeDate(Date date, int diffDay)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(5, diffDay);
    date = c.getTime();
    return date;
  }
  
  public static String dateToString(Date d)
  {
    return dateToString(d, "yyyy-MM-dd");
  }
  
  public static String dateToString(Date d, String format)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(d);
  }
  
  public static Date strToDate(String dateStr)
  {
    return strToDate(dateStr, "MM/dd/yyyy");
  }
  
  public static Date strParseDate(String dateStr)
  {
    return strParseDate(dateStr, "yyyy-MM-dd");
  }
  
  public static Date strToDate(String dateStr, String format)
  {
    if (StringUtils.isEmpty(dateStr)) {
      return null;
    }
    SimpleDateFormat dateFmt = new SimpleDateFormat(format);
    
    dateFmt.setLenient(false);
    ParsePosition pos = new ParsePosition(0);
    return dateFmt.parse(dateStr, pos);
  }
  
  public static Date strParseDate(String dateStr, String format)
  {
    if (StringUtils.isEmpty(dateStr)) {
      return null;
    }
    SimpleDateFormat dateFmt = new SimpleDateFormat(format);
    try
    {
      return dateFmt.parse(dateStr);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static boolean compareDate(Date date1, Date date2)
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return simpleDateFormat.format(date1).equals(simpleDateFormat.format(date2));
  }
  
  public static String getDayOfWeek(Date date)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int dayOfWeek = c.get(7);
    String DayOfWeek = "";
    switch (dayOfWeek)
    {
    case 1: 
      DayOfWeek = "Sun";
      break;
    case 2: 
      DayOfWeek = "Mon";
      break;
    case 3: 
      DayOfWeek = "Tue";
      break;
    case 4: 
      DayOfWeek = "Wed";
      break;
    case 5: 
      DayOfWeek = "Thu";
      break;
    case 6: 
      DayOfWeek = "Fri";
      break;
    case 7: 
      DayOfWeek = "Sat";
    }
    return DayOfWeek;
  }
  
  public static Integer getIndexOfDayOfWeek(Date date)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int dayOfWeek = c.get(7);
    return Integer.valueOf(dayOfWeek);
  }
  
  public static Integer getWeekOfYear(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return Integer.valueOf(cal.get(3));
  }
  
  public static boolean checkTodayPassHalfMonth(Date date)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int day = calendar.get(5);
    if (day >= 15) {
      return true;
    }
    return false;
  }
  
  public static Date addMinutes(Date date, int amount)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(12, amount);
    return c.getTime();
  }
  
  public static Date addHours(Date date, int amount)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(11, amount);
    return c.getTime();
  }
  
  public static Date addDays(Date date, int amount)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(5, amount);
    return c.getTime();
  }
  
  public static Date addMonths(Date date, int amount)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(2, amount);
    return c.getTime();
  }
  
  public static Integer getAge(Date birth)
  {
    if (birth == null) {
      return Integer.valueOf(0);
    }
    Calendar cal = Calendar.getInstance();
    if (cal.before(birth)) {
      return Integer.valueOf(0);
    }
    int yearNow = cal.get(1);
    int monthNow = cal.get(2) + 1;
    int dayOfMonthNow = cal.get(5);
    
    cal.setTime(birth);
    int yearBirth = cal.get(1);
    int monthBirth = cal.get(2) + 1;
    int dayOfMonthBirth = cal.get(5);
    
    int age = yearNow - yearBirth;
    if (monthNow <= monthBirth) {
      if (monthNow == monthBirth)
      {
        if (dayOfMonthNow < dayOfMonthBirth) {
          age--;
        }
      }
      else {
        age--;
      }
    }
    return Integer.valueOf(age);
  }
}
