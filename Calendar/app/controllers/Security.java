package controllers;

import models.Database;
 
public class Security extends Secure.Security {
    
    static boolean authenticate(String username, String password) {
		Database database = Database.getInstance();
		return database.checkLogin(username, password);
	}
    static boolean authentify(String username, String password){
    	return authenticate(username, password);
    }
   /*static void onAuthenticated(){
    	try {
			Secure.login();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
    static void onDisconnected() {
    	try {
			Secure.login();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
