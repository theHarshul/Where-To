package org.harshul.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	private int id;
	private String email, password;
	private int budget;
	
	public User(){
		
	}
	
	public User(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	
	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;		
	}

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	

}
