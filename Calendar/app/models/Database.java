package models;

import java.util.ArrayList;

public class Database {

	public ArrayList<User> users;
	public static Database database;
	
	public Database(){
		users = new ArrayList<User>();
		
	}
	public User getUserByName(String name){
		User searchedUser = new User();
		boolean found=false;
		for(User user: users){
			if (user.getName().equals(name)){
					searchedUser = user;
					found = true;
			}
		}
		if(found)
			return searchedUser;
		else
			return null;
	}
	/**
	 * @param user to register
	 * @return false if there is another user with the same name. true if not.
	 */
	public boolean registerNewUser(User user){
		if(getUserByName(user.getName()) != null)
			return false;
		else{
			users.add(user);
			return true;
		}
	}
	public static Database getInstance(){
		if(database== null)
			return database = new Database();
		else
			return database;
		
	}
	public ArrayList<User> getUsers(){
		return this.users;
	}
	public boolean checkLogin(String userName, String password){
		User user = getUserByName(userName);
		if(user== null){
			return false;
		}
		return (user.getPassword().equals(password));			
	}
	
	
}
