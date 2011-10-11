
import static org.junit.Assert.*;
import java.util.GregorianCalendar;

import models.Event;

import org.junit.Before;
import org.junit.Test;

import play.test.FunctionalTest;
import models.*;

public class EventTest  extends FunctionalTest{

	private Event event0;
	private Event event1;
	private GregorianCalendar date0;
	private GregorianCalendar date1;
	
	
	@Before
	public void before(){
		date0 = new GregorianCalendar(2000,0,1,10,0);
		date1 = new GregorianCalendar(2001,1,2,11,30);
		event0 = new Event("event0", date0, date1, false);
		
	}
	
	@Test
	public void ShouldGetStartAndEndDate(){
		assert( event0.getStartDate() == date0);
		assert( event0.getEndDate() == date1);
	}
	@Test
	public void startDateNotAfterEndDate() {
		assert !event0.getStartDate().after(event0.getEndDate());
		boolean creatingEventFailed = false;
		try{
			event1 = new Event("event0", date1, date0, false);
		}
		catch(AssertionError e){
			creatingEventFailed = true;
		}
		assert creatingEventFailed;
	}

	//check if assertions are enabled
	@Test
	public void shouldFail(){
		assert false;
	}
}
