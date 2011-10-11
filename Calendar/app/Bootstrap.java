import java.util.GregorianCalendar;

import models.*;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
import play.*;
import play.jobs.*;
import play.test.*;

@OnApplicationStart
public class Bootstrap extends Job{
	
	public void doJob(){
		Database database = Database.getInstance();
		User userAlrik = new User("Alrik", "1234");
		Calendar calendarA0 = userAlrik.createCalendar();
		calendarA0.addEvent(new Event("Carmina Burana", new GregorianCalendar(2011,9, 25,18,30), new GregorianCalendar(2011,10, 25,20,0), false), userAlrik);
		calendarA0.addEvent(new Event("Exam", new GregorianCalendar(2011,5, 25,9,30), new GregorianCalendar(2011,8, 25,20,0), true), userAlrik);
		
		User userHal = new User("Hal", "abc");
		Calendar calendarH0 = userHal.createCalendar();
		Calendar calendarH1 = userHal.createCalendar();
		calendarH0.addEvent(new Event("Date with Frobo", new GregorianCalendar(1999,2, 3,10,30), new GregorianCalendar(1999,2, 3,12,00), true), userHal);
		calendarH0.addEvent(new Event("Carmina Burana", new GregorianCalendar(2011,9, 25,18,30), new GregorianCalendar(2011,10, 25,20,30), false), userHal);
		calendarH0.addEvent(new Event("Carmina Burana2", new GregorianCalendar(2011,9, 25,18,32), new GregorianCalendar(2011,9, 25,20,32), false), userHal);
		calendarH1.addEvent(new Event("DDOS Facebook", new GregorianCalendar(2011,8, 5,20,0), new GregorianCalendar(2011,8, 5,20,1), true), userHal);
		
		
		database.registerNewUser(userAlrik);
		database.registerNewUser(userHal);
								
	}
	
}
