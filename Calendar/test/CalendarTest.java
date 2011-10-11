
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import models.Calendar;
import models.Event;
import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.FunctionalTest;
import models.*;

public class CalendarTest  extends FunctionalTest{
	private Calendar calendar;
	private User user;
	private User user1; 
	private GregorianCalendar date0 = new GregorianCalendar(2001,GregorianCalendar.JANUARY,1,10,30);
	private GregorianCalendar date1 = new GregorianCalendar(2001,GregorianCalendar.JANUARY,2,9,00);
	private GregorianCalendar date2 = new GregorianCalendar(2001,GregorianCalendar.JANUARY,3,11,30);
	private GregorianCalendar date3 = new GregorianCalendar(2001,GregorianCalendar.JANUARY,4,12,00);
	
	@Before
	public void before(){
		user = new User();
		user1 = new User();
		calendar= user.createCalendar();
		
	}
	
	@Test
	public void shouldHaveOwner(){
		assert(calendar.getUser() != null);
	}
	
	@Test
	public void shouldHaveName() {
		assertEquals(calendar.getName(), "'s Calendar");
	}
	
	@Test
	public void shouldBeOwnedByOwner(){
		assert( calendar.getUser().getCalendars().contains(calendar));
	}
	
	@Test
	public void sholdHaveEvents(){ //TODO: only tests the number of dates with events, but not the number of events per day
		calendar.addEvent(new Event("event0", date0, date1, true),user);
		calendar.addEvent(new Event("event0", date1, date1, false), user);
		assertEquals(calendar.getAllEvents(user).size(), 2);
		
	}
	
	@Test
	public void shouldOnlyBeAccessableByOwner(){
		try{
			calendar.getAllEvents(user);
		}
		catch(AssertionError e){
			assert false;
		}
		boolean assertionErrorHappend = false;
		try{
			calendar.getAllEvents(user1);
		}
		catch(AssertionError e){
			assertionErrorHappend = true;
		}
		assert(assertionErrorHappend);
	}
	
	@Test //TODO: same as shouldHaveEvents() see above
	public void allEventsShouldContainPublicEvents(){
		calendar.addEvent(new Event("event0", date0, date1, true), user);
		calendar.addEvent(new Event("event1", date1, date2, false), user);
		calendar.addEvent(new Event("event2", date1, date3, true), user);
		calendar.addEvent(new Event("event3", date2, date3, false), user);
		assert (calendar.getAllEvents(user).size() > calendar.getPublicEvents().size());
		assertEquals (calendar.getPublicEvents().size(), 2);
		assertEquals (calendar.getAllEvents(user).size(), 3);
	}
	
	@Test
	public void shouldCloneArrayList(){
		ArrayList list0 = new ArrayList();
		ArrayList list1 = new ArrayList();
		list1.add(1);
		list1.add(2);
		ArrayList clone0 = calendar.deepCloneArrayList(list0);
		assertEquals( clone0.size(), 0);
		assert(clone0.containsAll(list0) && list0.containsAll(clone0));
		clone0 = calendar.deepCloneArrayList(list1);
		assertEquals( clone0.size(), 2);
		assert(clone0.containsAll(list1) && list1.containsAll(clone0));
		assert(clone0.containsAll(list0));
		assertFalse(list0.containsAll(clone0));
	}
	
	@Test
	public void shouldListEventsOfOneDay(){
		Event event0 = new Event("event0", date0, date1, false);
		Event event1 = new Event("event1", date1, date2, true);
		Event event2 = new Event("event2", date1, date3, false);
		Event event3 = new Event("event3", date3, date3, true);
		calendar.addEvent(event0, user);
		calendar.addEvent(event1, user);
		calendar.addEvent(event2, user);
		calendar.addEvent(event3, user);
		
		ArrayList<Event> allEvents = calendar.listOneDaysEvents(date1, user);
		assertEquals(allEvents.size(), 2);
		assert(allEvents.contains(event1));
		assert(allEvents.contains(event2));
		allEvents = calendar.listOneDaysEvents(date1, user1);
		assertEquals(allEvents.size(), 1);
		assertFalse(allEvents.contains(event1));
		assert(allEvents.contains(event2));
	}
	
	@Test
	public void shouldIterate(){
		Event event0 = new Event("event0", date0, date1, false);
		Event event1 = new Event("event1", date1, date1, true);
		Event event2 = new Event("event2", date1, date3, false);
		Event event3 = new Event("event3", date3, date3, true);
		calendar.addEvent(event0, user);
		calendar.addEvent(event1, user);
		calendar.addEvent(event2, user);
		calendar.addEvent(event3, user);
		
		Iterator<Event> iterator0 = calendar.iterateFromDate(date1, user);
		Iterator<Event> iterator1 = calendar.iterateFromDate(date1, user1);
		assertFalse(iterator0.equals(iterator1));
		int counter0 = 0;
		int counter1 = 0;
		Event eventx;
		while(iterator0.hasNext()){
			eventx = iterator0.next();
			assert ( eventx == event1 || eventx == event2 || eventx == event3 );
			counter0++;
		}
		assertEquals(counter0,3);
		while(iterator1.hasNext()){
			assert (iterator1.next() == event2);
			counter1++;
		}
		assertEquals(counter1,1);
	}
	
	@Test
	public void shouldReturnBeginningOfDay(){
		assert( calendar.getBeginningOfDay(date2).equals(new GregorianCalendar(2001,GregorianCalendar.JANUARY,3,0,0)));
	}
	
	@Test
	public void sholdFail(){
		assert false;
	}

}
