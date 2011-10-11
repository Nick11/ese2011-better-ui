package models;

import java.util.GregorianCalendar;
import java.util.Locale;


public class Event {

	private String name;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private boolean isPrivate;
	private int id;
	private static int generalId = 0;
	/**
	 * 
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param isPrivate
	 */
	public Event(String name, GregorianCalendar startDate, GregorianCalendar endDate, boolean isPrivate){
		assert(!startDate.after(endDate)); //startDate <= endDate should be possible.
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isPrivate = isPrivate;
		this.id =generalId;
		generalId++;
	}
	public boolean isPrivate(){
		return this.isPrivate;
	}
	
	//getters, setters
	public GregorianCalendar getStartDate(){
		return this.startDate;
	}
	public GregorianCalendar getEndDate(){
		return this.endDate;
	}
	public String getName(boolean pretty){
		return (pretty? this.name:this.name.replaceAll(" ","_"));
	}
	public String getStartDateString(boolean pretty){
		String date = "";
		String day = this.startDate.get(GregorianCalendar.DATE)+"";
		day = (day.length()<2?0+day:day);
		date = date+day+(pretty?". ":".");
		String month = this.startDate.get(GregorianCalendar.MONTH)+1+"";
		month = (month.length()<2?0+month:month);
		date = date+(pretty? this.startDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.GERMAN)+" ": month+".");
		date = date+this.startDate.get(GregorianCalendar.YEAR)+(pretty?"; ":";");
		String hour = ""+this.startDate.get(GregorianCalendar.HOUR_OF_DAY);
		hour = (hour.length()<2? 0+hour:hour);
		date = date+hour+":";
		String minute = ""+this.startDate.get(GregorianCalendar.MINUTE);
		date = date+(minute.length()<2 ?0+minute:minute) ;
		return date;
	}
	public String getEndDateString(boolean pretty){
		String date = "";
		String day = this.endDate.get(GregorianCalendar.DATE)+"";
		day = (day.length()<2?0+day:day);
		date = date+day+(pretty?". ":".");
		String month = this.endDate.get(GregorianCalendar.MONTH)+1+"";
		month = (month.length()<2?0+month:month);
		date = date+(pretty? this.endDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.GERMAN)+" ": month+".");
		date = date+this.endDate.get(GregorianCalendar.YEAR)+(pretty?"; ":";");
		String hour = ""+this.endDate.get(GregorianCalendar.HOUR_OF_DAY);
		hour = (hour.length()<2? 0+hour:hour);
		date = date+hour+":";
		String minute = ""+this.endDate.get(GregorianCalendar.MINUTE);
		date = date+(minute.length()<2 ?0+minute:minute) ;
		return date;
	}
	public int getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setStartDate(GregorianCalendar date){
		this.startDate = date;
	}
	public void setEndDate(GregorianCalendar date){
		this.endDate = date;
	}
	public void setPrivate(boolean isPrivate){
		this.isPrivate= isPrivate;
	}
}
