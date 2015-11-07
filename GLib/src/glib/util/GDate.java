package glib.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GDate {
	private DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
	private Calendar calendar = Calendar.getInstance();
	
	//CONSTRUCTORS
	
	public GDate(String date, String format){
		dateFormat = new SimpleDateFormat(format);
		try {
			calendar.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	//OTHERS
	
	@Override
	public String toString() {return dateFormat.format(calendar.getTime());}
	public String toString(String format){return new SimpleDateFormat(format).format(calendar.getTime());}
	
	//SETTERS
	
	public void setDate(String date, String format){
		setFormat(format);
		setDate(date);
	}
	
	public void setFormat(String format){
		dateFormat = new SimpleDateFormat(format);
	}
	
	public void setDate(String date){
		try {
			calendar.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	//GETTERS
	
	public long getTime(){return calendar.getTime().getTime();}
	
	//ADDERS

	public void addSecond(int seconds){calendar.add(Calendar.SECOND, seconds);}
	public void addMinutes(int minutes){calendar.add(Calendar.MINUTE, minutes);}
	public void addHours(int hours){calendar.add(Calendar.HOUR, hours);}
	public void addDays(int days){calendar.add(Calendar.DATE, days);}
	public void addMonths(int months){calendar.add(Calendar.MONTH, months);}
	public void addYears(int years){calendar.add(Calendar.YEAR, years);}
}
