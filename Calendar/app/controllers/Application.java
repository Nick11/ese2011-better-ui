package controllers;

import play.*;
import play.mvc.*;
import play.test.Fixtures;
import java.util.*;
import org.jboss.netty.bootstrap.Bootstrap;
import models.*;
import models.Calendar;
import play.data.validation.*;

@With(Secure.class)
public class Application extends Controller {
private static Database database = Database.getInstance();
    public static void index() {
    	
    	User user = database.getUserByName(Security.connected());
    	ArrayList<User> users = database.getUsers();
    	render(user,users);
    }
    
    public static void calendar(int calendarIndex, String otherUserName, GregorianCalendar selectedDate){
    	GregorianCalendar today = new GregorianCalendar();
    	ArrayList<Integer> listOfDaysInMonth = new ArrayList<Integer>();
    	for(int i=1; i<=today.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++ ){
    		listOfDaysInMonth.add(i);
    	}
    	User otherUser = database.getUserByName(otherUserName);
    	User user = database.getUserByName(Security.connected());
    	Calendar calendar = otherUser.getCalendars().get(calendarIndex);
    	ArrayList<Event> listOfEvents = calendar.listOneDaysEvents(selectedDate, user);
    	
    	render(calendar, listOfEvents, user, otherUser, today, listOfDaysInMonth, calendarIndex, selectedDate);
    }
    public static void userPage(String userName){
    	User otherUser = database.getUserByName(userName);
    	User user = database.getUserByName(Security.connected());
    	render(otherUser, user);
    }
    public static void addEvent(int calendarIndex){
    	User user = database.getUserByName(Security.connected());
    	Calendar calendar = user.getCalendars().get(calendarIndex);
    	Event event = new Event("null", new GregorianCalendar(),new GregorianCalendar(),false);
    	render(calendar, user, event);
    }
    public static void addedEvent(int calendarIndex, @Required(message="Name required")  String name, @Required String startDate, @Required String endDate, @Required boolean isPrivate){
    	User user = database.getUserByName(Security.connected());
    	Calendar calendar = user.getCalendars().get(calendarIndex);
    	
    	Event event = new Event("null", new GregorianCalendar(),new GregorianCalendar(),false);
    	GregorianCalendar endDatum = new GregorianCalendar();
    	GregorianCalendar startDatum = new GregorianCalendar();
    	try{
    		startDatum = convertToCalendar(startDate);
    	}
    	catch(Exception  e){
    		validation.addError(startDate, "Wrong form in startDate");
    	}
    	try{
    		 endDatum = convertToCalendar(endDate);
    	}
    	catch(Exception  e){
    		validation.addError(startDate, "Wrong form in endDate");
    	}
    	if(endDatum.before(startDatum))
    		validation.addError(endDate, "EndDate not after startDate");
    		
    	if (validation.hasErrors()) {
    		//validation.keep();
            render("Application/addEvent.html", calendar, user, event);
         }
    
    	calendar.addEvent(new Event(name, convertToCalendar(startDate), convertToCalendar(endDate), isPrivate), user);
    	
    	Application.calendar(user.getCalendars().indexOf(calendar), user.getName(), convertToCalendar(startDate));
    }
    private static GregorianCalendar convertToCalendar(String string){
    	
    	int year = Integer.valueOf(string.substring(6,10));
    	int month = Integer.valueOf(string.substring(3,5))-1;
    	int day = Integer.valueOf(string.substring(0,2));
    	int hour = Integer.valueOf(string.substring(11,13));
    	int minute = Integer.valueOf(string.substring(14,16));
    	
    	GregorianCalendar calendar = new GregorianCalendar(year,month,day,hour,minute);
    	return calendar;
    }
    public static void editEvent(int calendarIndex, int id){
    	User user = database.getUserByName(Security.connected());
    	Calendar calendar = user.getCalendars().get(calendarIndex);
    	Event event = calendar.getEventById(id, user);

    	render(calendar, user, event);
    }
    public static void editedEvent(int calendarIndex, int id, @Required(message="Name Required") String name, @Required String startDate, @Required String endDate, @Required boolean isPrivate){
    	User user = database.getUserByName(Security.connected());
    	Calendar calendar = user.getCalendars().get(calendarIndex);
    	Event event = calendar.getEventById(id, user);
    	GregorianCalendar endDatum = new GregorianCalendar();
    	GregorianCalendar startDatum = new GregorianCalendar();
    	try{
    		startDatum = convertToCalendar(startDate);
    	}
    	catch(Exception  e){
    		validation.addError(startDate, "Wrong form in startDate");
    	}
    	try{
    		 endDatum = convertToCalendar(endDate);
    	}
    	catch(Exception  e){
    		validation.addError(startDate, "Wrong form in endDate");
    	}
    	if(endDatum.before(startDatum))
    		validation.addError(endDate, "EndDate not after startDate");
    		
    	if (validation.hasErrors()) {
    		//validation.keep();
            render("Application/editEvent.html", calendar, user, event);
         }
    
    	
    	
    	event.setName(name);
    	event.setStartDate(convertToCalendar(startDate));
    	event.setEndDate(convertToCalendar(endDate));
    	event.setPrivate(isPrivate);
    	
    	Application.calendar(user.getCalendars().indexOf(calendar), user.getName(), convertToCalendar(startDate));
    }
    
    public static void deleteEvent(int calendarIndex, int id){
    	User user = database.getUserByName(Security.connected());
    	Calendar calendar = user.getCalendars().get(calendarIndex);
    	Event event = calendar.getEventById(id, user);
    	calendar.removeEvent(event);
    	
    	calendar(calendarIndex, user.getName(), event.getStartDate());
    }
}