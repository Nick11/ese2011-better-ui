
import static org.junit.Assert.*;

import java.util.ArrayList;

import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.FunctionalTest;
import models.*;

public class UserTest  extends FunctionalTest{

	User user;
	@Before
	public void before(){
		user = new User();
		
	}
	@Test
	public void shouldHaveName() {
		user.setName("Alrik");
		String name = user.getName();
		assert(name == "Alrik");
	}
	@Test
	public void shouldHaveCalendars(){
		user.createCalendar();
		user.createCalendar();
		assert(user.getCalendars().size() == 2);
		assertEquals(user.getCalendars().size(), 2);
	}
	@Test //to make sure, that the standard implementation is correct in this case
	public void shouldOnlyEqualItself(){
		User user1 = new User();
		assert(user.equals(user));
		assertFalse(user.equals(user1));
		User[] users = new User[]{user,user1};
		assert(users[0].equals(users[0]));
		assertFalse(users[0].equals(users[1]));
	}
	
	
	@Test
	public void shouldFail(){
		assert false;
	}

}
