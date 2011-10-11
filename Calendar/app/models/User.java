package models;

import java.util.ArrayList;

public class User {

	public String name;
	public String password;
	public ArrayList<Calendar> calendars;
	public boolean isLoggedIn;
	
	public User(){
		name = "";
		password = "";
		calendars = new ArrayList<Calendar>();
		isLoggedIn = false;
	}
	public User(String name, String password){
		this.name = name;
		this.password = password;
		calendars = new ArrayList<Calendar>();
		isLoggedIn = false;
	}
	
	public Calendar createCalendar(){
		Calendar calendar = new Calendar(this);
		calendars.add(calendar);
		return calendar;
	}
	
	
	//getters and setters
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	public ArrayList<Calendar> getCalendars(){
		return this.calendars;
	}
	public String getPassword(){
		return this.password;
	}
}
