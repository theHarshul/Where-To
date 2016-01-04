package org.harshul.services;

import java.util.ArrayList;

import org.harshul.model.User;
import org.harshul.mysql.MySQLDriver;

public class UserService {
	
	private static MySQLDriver driver = new MySQLDriver();
	
	
	public UserService() {
		
	}
	
	public ArrayList<User> getAllUsers(){
		driver.connect();
		ArrayList<User> users = new ArrayList<User>();
		users = driver.getAllUsers();
		if(users.isEmpty()){
			System.out.println("There are no users");
			driver.stop();
			return null;			
		}
		
		driver.stop();
		return users;
		
	}
	
	public boolean getUser(String username, String password){
		driver.connect();
		boolean exists = driver.verifyUser(username, password);
		driver.stop();
		return exists;
	}
	
	public User addUser(User u){
		driver.connect();
		driver.addUser(u.getEmail(), u.getPassword());
		u.setId(driver.numberOfUsers());
		driver.stop();
		return u;
	}
	
	
	
	

}
