package myyl.com.myyl.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class TimeUtil {
	
	public static Date getDateFromString(String dateString, String formatString){
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		Date result = null;
		try{result = format.parse(dateString);}catch(Exception e){e.printStackTrace();}
		return result;
	}
	
	public static String getStringFromDate(Date date, String formatString){
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		return format.format(date);
	}
	
	public static String getStringFromDate(Calendar calendar, String formatString){
		return getStringFromDate(calendar.getTime(), formatString);
	}
	
	public static String getWeekFromDate(Date date){
		String returnValue = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int currentWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if(currentWeek==1) returnValue = "周日";
		if(currentWeek==2) returnValue = "周一";
		if(currentWeek==3) returnValue = "周二";
		if(currentWeek==4) returnValue = "周三";
		if(currentWeek==5) returnValue = "周四";
		if(currentWeek==6) returnValue = "周五";
		if(currentWeek==7) returnValue = "周六";
		return returnValue;
	}
	
	public static String getCurrentDateString(String formatString){
        SimpleDateFormat format=new SimpleDateFormat(formatString);
        Calendar calendar = Calendar.getInstance();
		return format.format(calendar.getTime());
	}
	
	public static Date getCurrentDate(){
		return Calendar.getInstance().getTime();
	}
	
	public static Date getProtoDate(Date inDate){
		String temp = TimeUtil.getStringFromDate(inDate, "yyyy-MM-dd");
		return TimeUtil.getDateFromString(temp, "yyyy-MM-dd");
	}
	
	public static Date getProtoDateWithOpenTime(Date inDate, String HHmmss){
		String temp = TimeUtil.getStringFromDate(inDate, "yyyyMMdd");
		return TimeUtil.getDateFromString(temp+HHmmss, "yyyyMMddHHmmss");
	}
	
	public static String getLastMoneyDate(String in){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDateFromString(in, "yyyy-MM-dd"));
		calendar.add(Calendar.DATE, 30);
		return getStringFromDate(calendar.getTime(), "yyyy-MM-dd");
	}

	public static Date getDateFromDate(Date in, int inc){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(in);
		calendar.add(Calendar.DATE, inc);
		return calendar.getTime();
	}
	
	public static Date getDateFromDateMonth(Date in, int inc){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(in);
		calendar.add(Calendar.MONTH, inc);
		return calendar.getTime();
	}
	
	public static Date getDateFromDateStr(String in, String format, int inc){
		Date date = getDateFromString(in, format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, inc);
		return calendar.getTime();
	}

	public static ArrayList<String> getDataArrays(String format, int i) {
		ArrayList<String> out = new ArrayList<String>();
		for (int j = 0; j < i; j++) {
			String temp = getStringFromDate(getDateFromDate(getCurrentDate(), 0-j), format);
			out.add(temp);
		}
		return out;
	}
	
}
